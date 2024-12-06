// Переключение между вкладками
document.addEventListener('DOMContentLoaded', () => {
    const tabButtons = document.querySelectorAll('.tab-btn');
    const tabPanels = document.querySelectorAll('.tab-panel');

    tabButtons.forEach(button => {
        button.addEventListener('click', () => {
            // Удаляем активный класс у всех кнопок
            tabButtons.forEach(btn => btn.classList.remove('active'));
            // Добавляем активный класс на текущую кнопку
            button.classList.add('active');

            // Скрываем все панели
            tabPanels.forEach(panel => panel.classList.remove('active'));
            // Показываем соответствующую панель
            const targetTab = document.getElementById(button.dataset.tab);
            targetTab.classList.add('active');
        });
    });
});

function updateFileName() {
    var fileInput = document.getElementById("file");
    var fileName = document.getElementById("file-name");
  
    if (fileInput.files.length > 0) {
      var selectedFileName = fileInput.files[0].name;
      if (selectedFileName.length > 10) {
        fileName.textContent = selectedFileName.slice(0, 10) + '...' + selectedFileName.slice(-6);
      } else {
        fileName.textContent = selectedFileName;
      }
    } else {
      fileName.textContent = "Файл не выбран";
    }
}