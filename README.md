Тестовое задание Ерохиной Арины.

В ходе тестового задания был написан backend для автоматизации учёта носков на складе магазина. 

Кладовщик должен иметь возможность:

1) учесть приход и отпуск носков;

2) узнать общее количество носков определенного цвета и состава в данный момент времени. 

Также в ходе задания проект был развернут на удаленном сервере, база данных поднята в докер-контейнере рядом.

Ссылка на swagger на запущенный на удаленном сервере проект:

http://45.10.43.201:8080/swagger-ui/index.html

Для локального запуска проекта с развернутой рядом базой данных требуется:

1) установить Docker
2) создать контейнер командой `docker run -d --name postgres -p 5433:5432 -e POSTGRES_PASSWORD=mysecretpassword -e PGDATA=/var/lib/postgresql/data/pgdata -v /custom/mount:/var/lib/postgresql/data postgres`