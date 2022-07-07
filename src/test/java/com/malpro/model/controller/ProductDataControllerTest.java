package com.malpro.model.controller;

import com.malpro.model.controller.ProductDataController;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Created by fahian on 03.07.22.
 */
@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class ProductDataControllerTest {

//    @Mock
//    private ISupplierService iSupplierService;
//    @Mock
//    private IProductService iProductService;
//    @Mock
//    private IProductDataMapper iProductDataMapper;
//    @Mock
//    private IProductMapper iProductMapper;
//    @Mock
//    private IEtimService iEtimService;
    @InjectMocks
    private ProductDataController productDataController;
//
//    @Test
//    @DisplayName("Create product - supplier not found test")
//    void createProductSupplierNotFoundTest(@Random ProductDataDto productDataDto,
//                                           @Random UUID supplierUuid) {
//        final var productDto = Mockito.mock(ProductDto.class);
//
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//        Mockito.when(iProductDataMapper.toProductDto(productDataDto)).thenReturn(productDto);
//        Mockito.when(iSupplierService.getSupplier(supplierUuid.toString())).thenReturn(Optional.empty());
//
//        ResponseEntity<String> responseEntity = productDataController.createProduct(productDataDto, supplierUuid.toString());
//
//        Mockito.verify(iProductDataMapper).toProductDto(productDataDto);
//        Mockito.verify(iSupplierService).getSupplier(supplierUuid.toString());
//        Mockito.verify(iEtimService, Mockito.never()).findEtimClassByCode(ArgumentMatchers.any());
//        Mockito.verify(iProductMapper, Mockito.never()).toProduct(productDto);
//        Mockito.verify(iProductService, Mockito.never()).createProduct(ArgumentMatchers.any(), ArgumentMatchers.any());
//
//        MatcherAssert.assertThat(responseEntity.getStatusCodeValue(), Matchers.is(404));
//    }
//
//    @Test
//    @DisplayName("Create product - etim class not found test")
//    void createProductEtimClassNotFoundTest(@Random ProductDataDto productDataDto,
//                                            @Random UUID supplierUuid) {
//        final var productDto = Mockito.mock(ProductDto.class);
//        final var supplier = Mockito.mock(Supplier.class);
//
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//        Mockito.when(iProductDataMapper.toProductDto(productDataDto)).thenReturn(productDto);
//        Mockito.when(iSupplierService.getSupplier(supplierUuid.toString())).thenReturn(Optional.of(supplier));
//        Mockito.when(iEtimService.findEtimClassByCode(productDataDto.getEtimClass())).thenReturn(Optional.empty());
//
//        ResponseEntity<String> responseEntity = productDataController.createProduct(productDataDto, supplierUuid.toString());
//
//        Mockito.verify(iProductDataMapper).toProductDto(productDataDto);
//        Mockito.verify(iSupplierService).getSupplier(supplierUuid.toString());
//        Mockito.verify(iEtimService).findEtimClassByCode(productDataDto.getEtimClass());
//        Mockito.verify(iProductMapper, Mockito.never()).toProduct(productDto);
//        Mockito.verify(iProductService, Mockito.never()).createProduct(ArgumentMatchers.any(), ArgumentMatchers.any());
//
//        MatcherAssert.assertThat(responseEntity.getStatusCodeValue(), Matchers.is(404));
//    }
//
//    @Test
//    @DisplayName("Create product - no relevant feature test")
//    void createProductNoRelevantFeatureTest(@Random ProductDataDto productDataDto,
//                                            @Random UUID supplierUuid) {
//        final var productDto = Mockito.mock(ProductDto.class);
//        final var product = Mockito.mock(Product.class);
//        final var supplier = Mockito.mock(Supplier.class);
//
//        final var etimClass = new EtimClass();
//        final var etimClassFeature = new EtimClassFeature();
//        final var etimFeature = new EtimFeature();
//        etimFeature.setDescription(RandomStringUtils.randomAlphabetic(10));
//        etimClassFeature.setFeature(etimFeature);
//        etimClass.setFeatures(List.of(etimClassFeature));
//
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//        Mockito.when(iProductDataMapper.toProductDto(productDataDto)).thenReturn(productDto);
//        Mockito.when(iSupplierService.getSupplier(supplierUuid.toString())).thenReturn(Optional.of(supplier));
//        Mockito.when(iEtimService.findEtimClassByCode(productDataDto.getEtimClass())).thenReturn(Optional.of(etimClass));
//        Mockito.when(iProductMapper.toProduct(productDto)).thenReturn(product);
//        Mockito.when(iProductService.createProduct(product, supplier)).thenReturn(product);
//
//        ResponseEntity<String> responseEntity = productDataController.createProduct(productDataDto, supplierUuid.toString());
//
//        Mockito.verify(iProductDataMapper).toProductDto(productDataDto);
//        Mockito.verify(iSupplierService).getSupplier(supplierUuid.toString());
//        Mockito.verify(iEtimService).findEtimClassByCode(productDataDto.getEtimClass());
//        Mockito.verify(iProductMapper).toProduct(productDto);
//        Mockito.verify(iProductService).createProduct(product, supplier);
//
//        MatcherAssert.assertThat(responseEntity.getStatusCodeValue(), Matchers.is(201));
//    }
//
//    @Test
//    @DisplayName("Create product test")
//    void createProductTest(@Random ProductDataDto productDataDto,
//                           @Random UUID supplierUuid) {
//        final var productDto = Mockito.mock(ProductDto.class);
//        final var product = Mockito.mock(Product.class);
//        final var supplier = Mockito.mock(Supplier.class);
//
//        final var etimClass = new EtimClass();
//        final var etimClassFeature = new EtimClassFeature();
//        final var etimFeature = new EtimFeature();
//        etimFeature.setDescription("test");
//        etimFeature.setType(EtimFeatureType.Logical);
//        etimClassFeature.setFeature(etimFeature);
//        etimClass.setFeatures(List.of(etimClassFeature));
//        productDataDto.getFeaturesMap().put(etimFeature.getDescription(), "true");
//
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//        Mockito.when(iProductDataMapper.toProductDto(productDataDto)).thenReturn(productDto);
//        Mockito.when(iSupplierService.getSupplier(supplierUuid.toString())).thenReturn(Optional.of(supplier));
//        Mockito.when(iEtimService.findEtimClassByCode(productDataDto.getEtimClass())).thenReturn(Optional.of(etimClass));
//        Mockito.when(iProductMapper.toProduct(productDto)).thenReturn(product);
//        Mockito.when(iProductService.createProduct(product, supplier)).thenReturn(product);
//
//        ResponseEntity<String> responseEntity = productDataController.createProduct(productDataDto, supplierUuid.toString());
//
//        Mockito.verify(iProductDataMapper).toProductDto(productDataDto);
//        Mockito.verify(iSupplierService).getSupplier(supplierUuid.toString());
//        Mockito.verify(iEtimService).findEtimClassByCode(productDataDto.getEtimClass());
//        Mockito.verify(iProductMapper).toProduct(productDto);
//        Mockito.verify(iProductService).createProduct(product, supplier);
//
//        MatcherAssert.assertThat(responseEntity.getStatusCodeValue(), Matchers.is(201));
//    }
}