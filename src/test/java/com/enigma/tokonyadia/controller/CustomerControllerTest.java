package com.enigma.tokonyadia.controller;

import com.enigma.tokonyadia.entity.Customer;
import com.enigma.tokonyadia.service.interfaces.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void isShouldCreateCustomerAndRetrunCustomerResponseOfCustomerAndStatusCode() throws Exception {
        //Data Dummy Customer
        Customer dummyCustomer = new Customer();
        dummyCustomer.setId("customer123");
        dummyCustomer.setName("Doni");

        //mock beaviornya
        when(customerService.create(any(Customer.class))).thenReturn(dummyCustomer);

        //Mengirimkan permintann HTTP ke endpoint controller
        mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dummyCustomer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(201))
                .andExpect(jsonPath("$.message").value("Successfully create new customer"))
                .andExpect(jsonPath("$.data.id").value(dummyCustomer.getId()))
                .andExpect(jsonPath("$.data.name").value(dummyCustomer.getName()));

        //verify
        verify(customerService , times(1)).create(any(Customer.class));
    }

    @Test
    void itShouldGetAllCustomerAndStatusOk() throws Exception {
        Customer dummyCustomer = new Customer();
        dummyCustomer.setId("customer123");
        dummyCustomer.setName("Doni");

        Customer dummyCustomer2 = new Customer();
        dummyCustomer2.setId("customer456");
        dummyCustomer2.setName("Stevano");

        List<Customer> dummyCustomers = Arrays.asList(dummyCustomer,dummyCustomer2);

        when(customerService.getAll()).thenReturn(dummyCustomers);

        mockMvc.perform(get("/api/v1/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value("Successfully get all customer"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].id").value(dummyCustomer.getId()))
                .andExpect(jsonPath("$.data[0].name").value(dummyCustomer.getName()))
                .andExpect(jsonPath("$.data[1].id").value(dummyCustomer2.getId()))
                .andExpect(jsonPath("$.data[1].name").value(dummyCustomer2.getName()));

        verify(customerService, times(1)).getAll();
    }

    @Test
    public void testGetCustomerById() throws Exception {
        // Data dummy pelanggan
        Customer dummyCustomer = new Customer();
        dummyCustomer.setId("customer123");
        dummyCustomer.setName("Dummy Customer");

        // Mengatur perilaku mock customerService.getById
        when(customerService.getById(eq("customer123"))).thenReturn(dummyCustomer);

        // Mengirimkan permintaan HTTP GET ke endpoint controller dengan ID pelanggan
        mockMvc.perform(get("/api/v1/customers/customer123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value("Successfully get customer by id"))
                .andExpect(jsonPath("$.data.id").value(dummyCustomer.getId()))
                .andExpect(jsonPath("$.data.name").value(dummyCustomer.getName()));

        // Verifikasi pemanggilan customerService.getById
        verify(customerService, times(1)).getById(eq("customer123"));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        // Data dummy pelanggan yang akan diperbarui
        Customer dummyCustomer = new Customer();
        dummyCustomer.setId("customer123");
        dummyCustomer.setName("Updated Dummy Customer");

        // Mengatur perilaku mock customerService.update
        when(customerService.update(any(Customer.class))).thenReturn(dummyCustomer);

        // Mengirimkan permintaan HTTP PUT ke endpoint controller
        mockMvc.perform(put("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dummyCustomer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value("Successfully update customer"))
                .andExpect(jsonPath("$.data.id").value(dummyCustomer.getId()))
                .andExpect(jsonPath("$.data.name").value(dummyCustomer.getName()));

        // Verifikasi pemanggilan customerService.update
        verify(customerService, times(1)).update(any(Customer.class));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        // ID pelanggan yang akan dihapus
        String customerId = "customer123";

        // Mengirimkan permintaan HTTP DELETE ke endpoint controller
        mockMvc.perform(delete("/api/v1/customers/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value("Successfully delete customer"));

        // Verifikasi pemanggilan customerService.deleteById
        verify(customerService, times(1)).deleteById(eq(customerId));
    }
}