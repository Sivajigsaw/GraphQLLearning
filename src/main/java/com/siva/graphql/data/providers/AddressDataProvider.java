package com.siva.graphql.data.providers;

import com.siva.graphql.util.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.siva.graphql.util.IdGenerator.generateAddressId;

@Component
public class AddressDataProvider {
    private static final List<Address> addressList = new ArrayList<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public record Address(String id, String addressLine1, String addressLine2, String zipCode, String city,
                          String state) {
    }

    public record AddressInput(String addressLine1, String addressLine2, String zipCode, String city,
                               String state){}

    static {
        Address a1 = new Address("A1", "42, address line one", null, "607100", "Essen", "NRW");
        Address a2 = new Address("A2", "43, address line one", null, "607101", "Dortmund", "NRW");
        Address a3 = new Address("A3", "44, address line one", null, "607101", "Dortmund", "NRW");
        Address a4 = new Address("A4", "45, address line one", null, "607104", "Duh", "NRW");
        addressList.add(a1);
        addressList.add(a2);
        addressList.add(a3);
        addressList.add(a4);
    }

    public Address getAddressById(String id) {
        readLock.lock();
        try {
            return addressList.stream().filter(address -> id.equals(address.id))
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("Not a valid Address Id"));
        } finally {
            readLock.unlock();
        }
    }

    public Address addAddress(AddressInput addressInput) {
        writeLock.lock();
        try {
            Address address = new Address(generateAddressId(), addressInput.addressLine1, addressInput.addressLine2, addressInput.zipCode, addressInput.city, addressInput.state);
            addressList.add(address);
            return address;
        } finally {
            writeLock.unlock();
        }
    }


}
