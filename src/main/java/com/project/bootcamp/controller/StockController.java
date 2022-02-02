package com.project.bootcamp.controller;


import com.project.bootcamp.model.dto.StockDTO;
import org.apache.tomcat.jni.Local;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/stock")
public class StockController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> post(@Valid @RequestBody StockDTO dto){
        return ResponseEntity.ok(dto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> put(@Valid @RequestBody StockDTO dto){
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockDTO>> findAll(){
        List<StockDTO> list = new ArrayList<>();

        StockDTO stock1 =  new StockDTO();
        stock1.setId(1L);
        stock1.setName("Americanas");
        stock1.setDate(LocalDate.now());
        stock1.setPrice(250D);
        stock1.setVariation(20D);
        list.add(stock1);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> findById(@PathVariable Long id) {
        List<StockDTO> listStocks = new ArrayList<>();

        StockDTO stock1 = new StockDTO();
        stock1.setId(1L);
        stock1.setName("Americanas");
        stock1.setDate(LocalDate.now());
        stock1.setPrice(250D);
        stock1.setVariation(10D);

        StockDTO stock2 = new StockDTO();

        stock2.setId(2L);
        stock2.setName("Amazon");
        stock2.setDate(LocalDate.now());
        stock2.setPrice(500D);
        stock2.setVariation(25D);

        listStocks.add(stock1);
        listStocks.add(stock2);

        StockDTO searchedStock = listStocks.stream().filter(x -> x.getId().compareTo(id) == 0).findFirst().get();

        return ResponseEntity.ok(searchedStock);
    }


}
