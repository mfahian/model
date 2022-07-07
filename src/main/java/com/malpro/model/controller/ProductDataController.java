package com.malpro.model.controller;

import com.malpro.model.configuration.ApiConfiguration;
import com.malpro.model.dto.ProductDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fahian on 20.05.22.
 */
@RestController
@RequestMapping(ApiConfiguration.API_URI_V1 + ProductDataController.URL)
@RequiredArgsConstructor
public class ProductDataController {
    public static final String URL = "/supplier/{supplierUuid}/product/textdata";

//    private final ISupplierService iSupplierService;
//    private final IProductService iProductService;
//    private final IProductDataMapper iProductDataMapper;
//    private final IProductMapper iProductMapper;
//    private final IEtimService iEtimService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createProduct(@RequestBody ProductDataDto productDataDto,
                                                @PathVariable String supplierUuid) {
//        ProductDto productDto = iProductDataMapper.toProductDto(productDataDto);
//
//        final Optional<Supplier> supplier = iSupplierService.getSupplier(supplierUuid);
//
//        if(supplier.isEmpty()) return new ResponseEntity<>("Supplier not found",HttpStatus.NOT_FOUND);
//
//        final Optional<EtimClass> etimClass = iEtimService.findEtimClassByCode(productDataDto.getEtimClass());
//
//        if(etimClass.isEmpty()) return new ResponseEntity<>("Etim class not found", HttpStatus.NOT_FOUND);
//
//        //todo procházet feature nebo mapu?
//        //todo ošetřit situaci, kdy feature map není
//        Set<ProductFeatureDto> productFeatureDtoSet = etimClass.get().getFeatures().stream()
//                .filter(ecf -> productDataDto.getFeaturesMap().containsKey(ecf.getFeature().getDescription()))
//                .map(ecf -> prepareFeature(ecf, productDataDto.getFeaturesMap().get(ecf.getFeature().getDescription())))
//                .collect(Collectors.toSet());
//
//        ProductFeaturesDto productFeaturesDto = ProductFeaturesDto.builder()
//                .etimGroup(productDataDto.getEtimClass())
//                .productFeature(productFeatureDtoSet)
//                .referenceFeatureSystem("ETIM_8_0")
//                .build();
//
//        productDto.setProductFeatures(List.of(productFeaturesDto));
//
//        Product product = iProductService.createProduct(iProductMapper.toProduct(productDto),supplier.get());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    private ProductFeatureDto prepareFeature(EtimClassFeature etimClassFeature, String inputValue) {
//        IFeatureFactory iFeatureFactory = ProductFeatureFactory.getFactory(etimClassFeature.getFeature().getType());
//
//        return iFeatureFactory.create(etimClassFeature, inputValue);
//    }

}
