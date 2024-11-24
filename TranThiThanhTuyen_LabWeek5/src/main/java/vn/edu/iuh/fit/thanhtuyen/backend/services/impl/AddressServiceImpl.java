package vn.edu.iuh.fit.thanhtuyen.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.thanhtuyen.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.thanhtuyen.backend.services.AddressService;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<String> findDistinctCity() {
        return addressRepository.findDistinctCity();
    }
}
