package br.com.alura.mvc.mudi.repository;

import br.com.alura.mvc.mudi.model.Order;
import br.com.alura.mvc.mudi.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus waiting);

    @Query("SELECT o FROM Order o JOIN o.user u WHERE u.username = :username")
    List<Order> findAllByUser(@Param("username") String username);

    @Query("SELECT o FROM Order o JOIN o.user u WHERE o.status = :status AND u.username = :username")
    List<Order> findByStatusAndUser(@Param("status") OrderStatus status, @Param("username") String username);
}
