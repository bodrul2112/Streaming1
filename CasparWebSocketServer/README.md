
to generate the spec from open api yaml, from root folder of this project run:

openapi-generator-cli generate -i src/main/resources/openapi.yaml -g kotlin-spring -c src/main/resources/openapiconfig.json --global-property models --global-property apis

