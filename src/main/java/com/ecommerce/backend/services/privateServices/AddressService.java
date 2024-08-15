package com.ecommerce.backend.services.privateServices;

import com.ecommerce.backend.models.entity.Address;
import com.ecommerce.backend.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    public AddressRepository repository;

    public String insertAddress(Address address){
        address.setCreatedOn(LocalDate.now());
        repository.save(address);
        return "Address added.";
    }

    public String insertManyAddresss(List<Address> addresss){
        repository.saveAll(addresss.stream().peek(x->x.setCreatedOn(LocalDate.now())).toList());
        return "Addresss added.";
    }

    public List<Address> selectAll(){
        return repository.findAll();
    }

    public Optional<Address> selectById(long id){
        return repository.findById(id);
    }

    public String updateAddress(long id, Address address){
        Optional<Address> addressExist = repository.findById(id);
        if(addressExist.isPresent()){
            Address existingAddress = addressExist.get();
            existingAddress.setNo(address.getNo());
            existingAddress.setCity(address.getCity());
            existingAddress.setState(address.getState());
            existingAddress.setCountry(address.getCountry());
            existingAddress.setPinCode(address.getPinCode());
            existingAddress.setAddressType(address.getAddressType());
            repository.save(existingAddress);
            return "Address Updated.";
        }
        return "address does not exist.";
    }

    public String deleteAddress(long id){
        repository.deleteById(id);
        return "Address deleted.";
    }
}
