.PHONY: run-test start stop status logs debug
.DEFAULT: start

start:
	docker-compose up -d

logs:
	docker-compose logs -ft

status:
	watch docker-compose ps

stop:
	docker-compose down

run-test:
	./gatling/bin/gatling.sh

debug:
	docker-compose stop consul-server-1
	docker-compose stop consul-server-2
	docker-compose stop consul-server-3
	docker-compose start consul-server-1
	docker-compose start consul-server-2
	docker-compose start consul-server-3
	docker-compose logs -ft consul-server-1 consul-server-2 consul-server-3
