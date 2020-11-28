.PHONY: run-test start stop
.DEFAULT: start

start:
	docker-compose up -d 
	docker-compose logs -ft

stop:
	docker-compose down

run-test:
	./gatling/bin/gatling.sh