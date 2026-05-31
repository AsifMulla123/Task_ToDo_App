const API_URL = "/api/tasks";

const form = document.querySelector("#taskForm");
const taskId = document.querySelector("#taskId");
const title = document.querySelector("#title");
const description = document.querySelector("#description");
const dueDate = document.querySelector("#dueDate");
const statusField = document.querySelector("#statusField");
const statusInput = document.querySelector("#status");
const formTitle = document.querySelector("#formTitle");
const formHint = document.querySelector("#formHint");
const submitButton = document.querySelector("#submitButton");
const cancelEdit = document.querySelector("#cancelEdit");
const formMessage = document.querySelector("#formMessage");
const statusMessage = document.querySelector("#statusMessage");
const taskList = document.querySelector("#taskList");
const refreshButton = document.querySelector("#refreshButton");
const filterButtons = document.querySelectorAll(".filter-button");
const openCount = document.querySelector("#openCount");
const closedCount = document.querySelector("#closedCount");

let tasks = [];
let activeFilter = "ALL";

function toInputDate(value) {
  if (!value) return "";
  return value.slice(0, 16);
}

function formatDate(value) {
  if (!value) return "No due date";
  return new Intl.DateTimeFormat(undefined, {
    dateStyle: "medium",
    timeStyle: "short"
  }).format(new Date(value));
}

function setMessage(element, text, type = "") {
  element.textContent = text;
  element.className = element === formMessage ? "message" : "status-message";
  if (type) element.classList.add(type);
}

function setEditing(task) {
  taskId.value = task.id;
  title.value = task.title || "";
  description.value = task.description || "";
  dueDate.value = toInputDate(task.dueDate);
  statusInput.value = task.status || "OPEN";
  formTitle.textContent = "Edit task";
  formHint.textContent = `Updating task #${task.id}.`;
  submitButton.textContent = "Save changes";
  statusField.classList.remove("hidden");
  cancelEdit.classList.remove("hidden");
  setMessage(formMessage, "");
  title.focus();
}

function resetForm() {
  form.reset();
  taskId.value = "";
  statusInput.value = "OPEN";
  formTitle.textContent = "New task";
  formHint.textContent = "Create a task with a future due date.";
  submitButton.textContent = "Create task";
  statusField.classList.add("hidden");
  cancelEdit.classList.add("hidden");
  setMessage(formMessage, "");
}

function taskPayload(includeStatus) {
  const payload = {
    title: title.value.trim(),
    description: description.value.trim(),
    dueDate: dueDate.value
  };

  if (includeStatus) {
    payload.status = statusInput.value;
  }

  return payload;
}

async function requestJson(url, options = {}) {
  const response = await fetch(url, {
    headers: { "Content-Type": "application/json" },
    ...options
  });

  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(errorText || `Request failed with status ${response.status}`);
  }

  if (response.status === 204) return null;
  const text = await response.text();
  if (!text) return null;

  try {
    return JSON.parse(text);
  } catch {
    return text;
  }
}

async function loadTasks() {
  statusMessage.classList.remove("hidden");
  setMessage(statusMessage, "Loading tasks...");
  taskList.innerHTML = "";

  try {
    tasks = await requestJson(API_URL);
    renderTasks();
  } catch (error) {
    setMessage(statusMessage, error.message, "error");
  }
}

function renderTasks() {
  const open = tasks.filter((task) => task.status === "OPEN").length;
  const closed = tasks.filter((task) => task.status === "CLOSED").length;
  openCount.textContent = open;
  closedCount.textContent = closed;

  const visibleTasks = tasks.filter((task) => activeFilter === "ALL" || task.status === activeFilter);

  if (!visibleTasks.length) {
    setMessage(statusMessage, tasks.length ? "No tasks match this filter." : "No tasks yet. Create one to see it here.");
    taskList.innerHTML = "";
    return;
  }

  setMessage(statusMessage, "");
  statusMessage.classList.add("hidden");
  taskList.innerHTML = visibleTasks.map((task) => `
    <article class="task-card">
      <div>
        <div class="task-title-row">
          <h3 class="task-title">${escapeHtml(task.title)}</h3>
          <span class="badge ${task.status === "CLOSED" ? "closed" : "open"}">${escapeHtml(task.status || "OPEN")}</span>
        </div>
        <p class="task-description">${escapeHtml(task.description || "No description")}</p>
        <div class="task-meta">
          <span>Due ${formatDate(task.dueDate)}</span>
          <span>Updated ${formatDate(task.updated)}</span>
        </div>
      </div>
      <div class="task-actions">
        <button class="ghost-button" type="button" data-action="toggle" data-id="${task.id}">
          ${task.status === "CLOSED" ? "Reopen" : "Close"}
        </button>
        <button class="ghost-button" type="button" data-action="edit" data-id="${task.id}">Edit</button>
        <button class="ghost-button danger-button" type="button" data-action="delete" data-id="${task.id}">Delete</button>
      </div>
    </article>
  `).join("");
}

function escapeHtml(value) {
  return String(value)
    .replaceAll("&", "&amp;")
    .replaceAll("<", "&lt;")
    .replaceAll(">", "&gt;")
    .replaceAll('"', "&quot;")
    .replaceAll("'", "&#039;");
}

async function saveTask(event) {
  event.preventDefault();

  const editing = Boolean(taskId.value);
  const url = editing ? `${API_URL}/${taskId.value}` : API_URL;
  const method = editing ? "PUT" : "POST";

  submitButton.disabled = true;
  setMessage(formMessage, editing ? "Saving task..." : "Creating task...");

  try {
    await requestJson(url, {
      method,
      body: JSON.stringify(taskPayload(editing))
    });
    setMessage(formMessage, editing ? "Task updated." : "Task created.", "success");
    resetForm();
    await loadTasks();
  } catch (error) {
    setMessage(formMessage, error.message, "error");
  } finally {
    submitButton.disabled = false;
  }
}

async function updateTaskStatus(task, nextStatus) {
  await requestJson(`${API_URL}/${task.id}`, {
    method: "PUT",
    body: JSON.stringify({
      title: task.title,
      description: task.description || "",
      dueDate: toInputDate(task.dueDate),
      status: nextStatus
    })
  });
}

taskList.addEventListener("click", async (event) => {
  const button = event.target.closest("button[data-action]");
  if (!button) return;

  const task = tasks.find((item) => String(item.id) === button.dataset.id);
  if (!task) return;

  if (button.dataset.action === "edit") {
    setEditing(task);
    return;
  }

  button.disabled = true;

  try {
    if (button.dataset.action === "delete") {
      await requestJson(`${API_URL}/${task.id}`, { method: "DELETE" });
    }

    if (button.dataset.action === "toggle") {
      await updateTaskStatus(task, task.status === "CLOSED" ? "OPEN" : "CLOSED");
    }

    await loadTasks();
  } catch (error) {
    setMessage(statusMessage, error.message, "error");
    statusMessage.classList.remove("hidden");
  } finally {
    button.disabled = false;
  }
});

filterButtons.forEach((button) => {
  button.addEventListener("click", () => {
    activeFilter = button.dataset.filter;
    filterButtons.forEach((item) => item.classList.toggle("active", item === button));
    statusMessage.classList.remove("hidden");
    renderTasks();
  });
});

form.addEventListener("submit", saveTask);
cancelEdit.addEventListener("click", resetForm);
refreshButton.addEventListener("click", loadTasks);

loadTasks();
