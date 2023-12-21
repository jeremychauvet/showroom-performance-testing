.PHONY: run-test up down watch logs debug
.DEFAULT: up

up:
	docker-compose up -d

logs:
	docker-compose logs -ft

watch:
	watch docker-compose ps

down:
	docker-compose down

run-test:
	./gatling/bin/gatling.sh

consul-rolling-update:
	docker-compose exec consul-server-1 consul reload
	docker-compose exec consul-server-2 consul reload
	docker-compose exec consul-server-3 consul reload
	docker-compose logs -ft consul-server-1 consul-server-2 consul-server-3
