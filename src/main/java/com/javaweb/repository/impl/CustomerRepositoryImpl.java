package com.javaweb.repository.impl;

import com.javaweb.model.CustomerSearchRequest;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.entity.CustomerEntity;
import com.javaweb.repository.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.javaweb.utils.StringUtil.checkString;
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @PersistenceContext// là annotation của JPA dùng để inject một EntityManager giống @Autowired
    private EntityManager entityManager;// là đỗi tượng cốt lõi dùng để quản lý  với cơ sở dữ liệu

    @Override
    public List<CustomerEntity> findAll(CustomerSearchRequest request) {
        StringBuilder sql = new StringBuilder("Select c from CustomerEntity c ");
        sql.append(" left Join c.users u ").append(" where c.id is not null ");
        if(request.getId() != null){
            sql.append(" AND c.id = " + request.getId());
        }
        if(checkString(request.getName())){
            sql.append(" AND c.name like '%" + request.getName() + "%'");
        }
        if(checkString(request.getPhone())){
            sql.append(" AND c.phone like '%" + request.getPhone() + "%'");
        }
        if(checkString(request.getEmail())){
            sql.append(" AND c.email like '%" + request.getEmail() + "%'");
        }


        Query query = entityManager.createQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();



    }

    @Override
    public void createCustomer(CustomerEntity customerEntity) {
        entityManager.persist(customerEntity);// hàm thêm vào database
    }

    @Override
    public void updateCustomer(CustomerEntity customerEntity) {
        entityManager.merge(customerEntity);
    }

    @Override
    public void deleteCustomer(Long id) {
        CustomerEntity customer = entityManager.find(CustomerEntity.class, id);

        if (customer == null) {
            return;
        }

        for (UserEntity user : new ArrayList<>(customer.getUsers())) {
            // Xóa Customer có id này khỏi danh sách customers của từng User
            // => Hibernate sẽ xóa bản ghi tương ứng trong bảng AssignmentCustomer
            user.getCustomers().remove(customer);
        }
        // Xóa toàn bộ User khỏi danh sách users của Customer
// => Hibernate sẽ xóa các bản ghi còn lại trong bảng AssignmentCustomer
        customer.getUsers().clear();// Trong danh sách nhân viên của khách hàng, xóa hết tất cả nhân viên.xóa bang trung gian


        entityManager.remove(customer);

    }

    @Override
    public CustomerEntity findById(Long id) {
        return  entityManager.find(CustomerEntity.class,id);
    }
    @Override
    public List<UserEntity> findById(List<Long> ids){
        List<UserEntity> result = new ArrayList<>();
        for(Long id:ids){
            result.add(entityManager.find(UserEntity.class,id));
        }
        return result;
    }
}
