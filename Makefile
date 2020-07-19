.PHONY: run-test launch-stack

launch-stack:
	docker-compose up -d 
	docker-compose logs -ft

run-test:
	./gatling/bin/gatling.sh