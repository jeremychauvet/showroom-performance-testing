FROM ghost:5-alpine

RUN apk add --no-cache --update --purge curl unzip && \
    curl -o consul.zip https://releases.hashicorp.com/consul/1.17.1/consul_1.17.1_linux_arm.zip  && \
    unzip consul.zip && \
    mv consul /usr/local/bin/ && \
    rm consul.zip && \
    consul --version
