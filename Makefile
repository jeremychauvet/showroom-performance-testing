.PHONY: run-test start
.DEFAULT: start

start:
	docker-compose up -d 
	docker-compose logs -ft

run-test:
	./gatling/bin/gatling.sh