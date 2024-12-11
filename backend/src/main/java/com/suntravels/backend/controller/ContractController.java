package com.suntravels.backend.controller;

import com.suntravels.backend.dto.ContractDto;
import com.suntravels.backend.dto.request.SearchRequestDto;
import com.suntravels.backend.dto.response.SearchResponseDto;
import com.suntravels.backend.service.ContractServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contract")
public class ContractController
{
    private final ContractServices contractServices;

    public ContractController(ContractServices contractServices) {
        this.contractServices = contractServices;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ContractDto>> getAllcontracts() {
        return ResponseEntity.status( HttpStatus.OK ).body( contractServices.getAllContracts() );
    }

    @GetMapping
    public ResponseEntity<ContractDto> getContract( @RequestParam(name = "id", required = false) Long id) {
        return ResponseEntity.status( HttpStatus.OK ).body( contractServices.getContract(id) );
    }

    @PostMapping
    public ResponseEntity<ContractDto> createContract(@RequestBody ContractDto contractDto) {
        ContractDto createdContract = contractServices.createContract(contractDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContract);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchResponseDto>> searchContract( @RequestBody SearchRequestDto searchRequestDto ) {
        List<SearchResponseDto> searchResponseDtos = contractServices.searchContracts(searchRequestDto);
        return ResponseEntity.status( HttpStatus.OK ).body( searchResponseDtos );
    }

}
