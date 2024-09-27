package vn.edu.iuh.fit.thanhtuyen.labweek2.services.impl;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.thanhtuyen.labweek2.dtos.ProductDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.dtos.ProductImageDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.dtos.ProductPriceDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.Product;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.ProductImage;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.ProductPrice;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.ProductPriceId;
import vn.edu.iuh.fit.thanhtuyen.labweek2.enums.ProductStatus;
import vn.edu.iuh.fit.thanhtuyen.labweek2.mappers.ProductImageMapper;
import vn.edu.iuh.fit.thanhtuyen.labweek2.mappers.ProductMapper;
import vn.edu.iuh.fit.thanhtuyen.labweek2.mappers.ProductPriceMapper;
import vn.edu.iuh.fit.thanhtuyen.labweek2.repositories.ProductImageRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek2.repositories.ProductPriceRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek2.repositories.ProductRepository;
import vn.edu.iuh.fit.thanhtuyen.labweek2.services.ProductService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductMapper productMapper;

    @Inject
    private ProductImageMapper productImageMapper;

    @Inject
    private ProductPriceRepository productPriceRepository;

    @Inject
    private ProductImageRepository productImageRepository;

    @Inject
    private ProductPriceMapper productPriceMapper;


    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(productMapper::toDto).orElse(null);
    }


    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        if (product.getId() != null) {
            Product oldProduct = productRepository.findById(product.getId()).orElse(null);
            if (oldProduct != null) {
                product = productMapper.partialUpdate(productDto, oldProduct);
            }
        }
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto updateStatus(Long id, ProductStatus status) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product productUpdate = productRepository.updateStatus(id, status);
            return productMapper.toDto(productUpdate);
        }
        return null;
    }

    @Override
    public List<ProductDto> findByManufacturer(String manufacturer) {
        return productRepository
                .findByManufacturer(manufacturer)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findByName(String name) {
        return productRepository
                .findByName(name)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findByStatus(ProductStatus status) {
        return productRepository
                .findByStatus(status)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto savePrice(ProductPriceDto productPriceDto) {
        ProductPrice productPrice = productPriceMapper.toEntity(productPriceDto);
        ProductPriceId productPriceId = productPrice.getId();
        Product product = productRepository.findById(productPriceId.getProductId()).orElse(null);
        if (product != null) {
            productPriceId.setPriceDateTime(LocalDateTime.now());
            productPrice.setId(productPriceId);
            productPrice.setProduct(product);
            productPrice = productPriceRepository.save(productPrice);
            product.setProductPrices(List.of(productPrice));
            return productMapper.toDto(product);
        }
        return null;
    }

    @Override
    public List<ProductDto> findByStatusAndLastPrice(ProductStatus status) {
        List<Product> products = productRepository.findByStatus(status);
        return products.stream().peek(product -> {
            ProductPrice productPrice = productPriceRepository.findLastPriceByProductId(product.getId());
            if (productPrice != null) {
                product.setProductPrices(List.of(productPrice));
            }
        }).map(productMapper::toDto).collect(Collectors.toList());
    }

}
