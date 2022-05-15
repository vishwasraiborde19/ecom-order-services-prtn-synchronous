package com.ecom.cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.cart.domain.Cart;
@Transactional()
public interface CartRepository extends JpaRepository<Cart, Long>{
	
		
	@Query(value="select max(cartId) from carts", nativeQuery=true)
	Integer getNeCartAggID();
	
	
	@Query(value="SELECT * FROM CARTS  where cartId = ?", nativeQuery=true)
	List<Cart> getCustomerCart(String cartId);
	
	@Query(value="SELECT * FROM CARTS  where sessionId = ? and productId = ?", nativeQuery=true)
	Cart getProductFromCustomerCart(String sessionId, Integer productId);
	
	@Modifying
	@Query(value="delete from carts where sessionId=? and productId =?", nativeQuery=true)
	void removeProductFromCart(String sessionId , Integer productId);
		
	// dev note: if the select arguments are less than the polo fields create a constructor to match the select fields in query
	@Query(value="select cartID, sessionId, cartId, productId, qty from carts where sessionId=? and productId=?", nativeQuery=true)
	Optional<List<Cart>> getCartSummary(String sessionId , Integer productId);

	

}

