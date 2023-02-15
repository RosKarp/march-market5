package ru.geekbrains.march.market.core.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.core.entities.Category;
import ru.geekbrains.march.market.core.entities.Order;
import ru.geekbrains.march.market.core.entities.Product;
import ru.geekbrains.march.market.core.integrations.CartServiceIntegration;
import ru.geekbrains.march.market.core.services.OrderService;
import ru.geekbrains.march.market.core.services.ProductService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private CartServiceIntegration cartServiceIntegration;

    @MockBean
    private ProductService productService;

    @Test
    public void createOrderTest() {
        CartDto cartDto = new CartDto();
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setPrice(BigDecimal.valueOf(12));
        cartItemDto.setPricePerProduct(BigDecimal.valueOf(6));
        cartItemDto.setProductId(234L);
        cartItemDto.setProductTitle("Bread");
        cartItemDto.setQuantity(2);
        cartDto.setTotalPrice(BigDecimal.valueOf(12));
        cartDto.setItems(List.of(cartItemDto));

        Mockito.when(cartServiceIntegration.getCurrentCart()).thenReturn(cartDto);

        Category category = new Category();
        category.setId(1L);
        category.setTitle("Food");

        Product product = new Product();
        product.setId(234L);
        product.setPrice(BigDecimal.valueOf(6));
        product.setTitle("Bread");
        product.setCategory(category);

        Mockito.doReturn(Optional.of(product)).when(productService).findById(234L);

        Order order = orderService.createOrder("Bill");
        Assertions.assertEquals(BigDecimal.valueOf(12), order.getTotalPrice());
        Assertions.assertEquals("Bread", order.getItems().get(0).getProduct().getTitle());
        Mockito.verify(cartServiceIntegration, Mockito.times(2)).getCurrentCart();
    }
}
