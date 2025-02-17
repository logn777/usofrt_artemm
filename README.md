# Сборка Docker-образа
docker build -t usofrt-app .

# Запуск контейнера
docker run -d -p 8080:8080 usofrt-app

# Swagger-UI
http://localhost:8080/swagger-ui.html
