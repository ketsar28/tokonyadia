package com.enigma.tokonyadia.impl;

import com.enigma.tokonyadia.entity.Store;
import com.enigma.tokonyadia.repository.StoreRepository;
import com.enigma.tokonyadia.service.impl.StoreServiceImpl;
import com.enigma.tokonyadia.service.interfaces.StoreService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class StoreServiceImplTest {
    //Mock StoreRepository
    private final StoreRepository storeRepository = mock(StoreRepository.class);

    //Create StoreService Instance
    private final StoreService storeService = new StoreServiceImpl(storeRepository);

    @Test
    void itShouldReturnStoreWhenCreateNewStore() {
        Store dummyStore = new Store();
        dummyStore.setId("123");
        dummyStore.setName("Berkah Selalu");

        //mock behavior repository save
        when(storeRepository.save(any(Store.class))).thenReturn(dummyStore);

        // Perform then create operation
        Store createStore = storeService.create(dummyStore);

        //verify repository.save method was called with expected argument
        verify(storeRepository, times(1)).save(dummyStore);

        //verify that the retrun store matches the expected store
        assertEquals("123" , createStore.getId());
        assertEquals("Berkah Selalu",createStore.getName());
    }

    @Test
    void itShouldGetDataStoreOneWhenGetByIdStore() {
        //Data dummy store
        String storeId = "1";
        Store dummyStore = new Store();
        dummyStore.setId(storeId);
        dummyStore.setName("Berkah Selalu");

        //mock behavior storeRepository.findById
        when(storeRepository.findById(storeId)).thenReturn(Optional.of(dummyStore));

        //perfom the getById operation
        Store retriviedStore = storeService.getById(storeId);

        //verify
        verify(storeRepository, times(1)).findById(storeId);

        //verify expected and actual
        assertEquals(storeId, retriviedStore.getId());
        assertEquals("Berkah Selalu", retriviedStore.getName());
    }

    @Test
    void itShouldGetAllDataStoreWhenCallGetAll() {
        List<Store> dummyStore = new ArrayList<>();
        dummyStore.add(new Store("1","123","Berkah Selalu","Ragunan","1234567890"));
        dummyStore.add(new Store("2","456","Selalu Jaya","Ragunan","123456780"));
        dummyStore.add(new Store("3","678","Maju Mundur","Ragunan","123456890"));

        when(storeRepository.findAll()).thenReturn(dummyStore);

        List<Store> retrivedStore = storeService.getAll();

        verify(storeRepository, times(1)).findAll();

        assertEquals(dummyStore.size(), retrivedStore.size());

        for (int i = 0; i < dummyStore.size(); i++) {
            assertEquals(dummyStore.get(i).getId() , retrivedStore.get(i).getId());
            assertEquals(dummyStore.get(i).getName() , retrivedStore.get(i).getName());
        }
    }

    @Test
    void update() {
        String storeId = "1";
        Store dummyStoreToUpdate = new Store(storeId,"123 (Update)" , "Store 1" , "Ragunan" , "12308559");

        when(storeRepository.findById(storeId)).thenReturn(Optional.of(new Store(storeId,"123" , "Store 1" , "Ragunan" , "12308559")));

        when(storeRepository.save(dummyStoreToUpdate)).thenReturn(dummyStoreToUpdate);

        Store updateStore = storeService.update(dummyStoreToUpdate);

        verify(storeRepository,times(1)).findById(storeId);
        verify(storeRepository,times(1)).save(dummyStoreToUpdate);

        assertEquals(dummyStoreToUpdate.getName() , updateStore.getName());
    }

    @Test
    void deleteById() {
        String storeId = "1";
        storeService.deleteById(storeId);
        verify(storeRepository, times(1)).deleteById(storeId);


    }
}