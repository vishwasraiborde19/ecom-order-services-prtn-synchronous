package com.ecom.delivery.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.delivery.domain.DeliveryDetails;
import com.ecom.delivery.service.DeliveryService;
import com.ecom.delivery.vo.DeliveryDetailsVO;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@RestController()
@RequestMapping("/deliveries")
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;



	@Value("classpath:delivery.graphqls")
	private Resource schemaResource;

	private GraphQL graphQL;
	
	@PostConstruct
	public void loadSchema() throws IOException {
		File schemaFile = schemaResource.getFile();
		TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}

	private RuntimeWiring buildWiring() {
		
		DataFetcher<List<DeliveryDetailsVO>> getAllFetcher = data -> {
			return (List<DeliveryDetailsVO>) deliveryService.getAll();
		};
		
		DataFetcher<List<DeliveryDetailsVO>> getDeliveryByIdFetcher = data -> {
			return (List<DeliveryDetailsVO>) deliveryService.findByPostCode(data.getArgument("postCode"));
		};

		return RuntimeWiring.newRuntimeWiring().type("Query",
				typeWriting -> typeWriting.dataFetcher("getAllDelivery", getAllFetcher).dataFetcher("findDelivery", getDeliveryByIdFetcher))
				.build();

	}
	
	@PostMapping(value = "/query")
	public ResponseEntity getAllDelivery(@RequestBody String query) {
		ExecutionResult result = graphQL.execute(query);
		System.out.println("result: " + result.getData());
		return ResponseEntity.ok(result.getData());
	}



	@GetMapping("/")
	public List<DeliveryDetailsVO> getAll() {
		return deliveryService.getAll();
	}
	
	@PostMapping("/addDelivery")
	public DeliveryDetailsVO addDelivery(@RequestBody DeliveryDetails deliveryDetails) {
		return deliveryService.addDelivery(deliveryDetails);
	}

	

}
