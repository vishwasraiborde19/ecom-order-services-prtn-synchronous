package com.ecom.cart.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.ecom.cart.domain.Cart;
import com.ecom.cart.repository.CartRepository;
import com.ecom.cart.vo.CartVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GuestUserCartService {

	@Autowired
	private CartRepository cartRepository;

	public CartVO removeProduct(String sessionid, Integer productId) {
		log.info("Removing Products" + sessionid + " : " + productId);
		cartRepository.removeProductFromCart(sessionid, productId);

		return new CartVO();
	}

	public List<CartVO> getCustomerCart(String cartId) {
		return cartRepository.getCustomerCart(cartId).stream().map(this::getValueObject).collect(Collectors.toList());
	}

	public List<CartVO> checkout(CartVO cart) {

		List<Cart> currentCartProduct = cartRepository.getCustomerCart(cart.getSessionId());

		return saveCartProducts(currentCartProduct);

	}

	private List<CartVO> saveCartProducts(List<Cart> cartProduct) {

		cartProduct.forEach(product -> product.setCartStatus("CHECKED_OUT"));
		cartRepository.saveAll(cartProduct);
		return cartProduct.stream().map(this::getValueObject).collect(Collectors.toList());
	}

	// for this example using server side session managed cart other alternative
	// could be with cookies
	public CartVO addorUpdateCartV2(CartVO cart) {

		List<Cart> customerCart = cartRepository.getCustomerCart(cart.getSessionId());

		// TODO is there way in java 11 to do this efficiently
		// map move code to function
		if (CollectionUtils.isEmpty(customerCart)) {
			// if no cart for user the create new cart 
			return createNewCart(cart);

		} else {
			log.info("Updating existing cart ");
			Cart currentCartProduct = cartRepository.getProductFromCustomerCart(cart.getSessionId(),
			        cart.getProductId());

			if (!ObjectUtils.isEmpty(currentCartProduct)) {
				// if cart exist with product update cart product line
				return updateExistingCartProducts(currentCartProduct, cart);
			} else {
				// if cart exist and does not have product create new  cart product line
				return addprodutsToExistingCart(currentCartProduct, cart);
			}

		}

	}

	private CartVO getValueObject(Cart cart) {
		CartVO vo = new CartVO();
		BeanUtils.copyProperties(cart, vo);
		return vo;
	}

	private CartVO createNewCart(CartVO cart) {
		Cart newCartLine = new Cart();
		newCartLine.setSessionId(cart.getSessionId());
		newCartLine.setCartId(getNewCartAggID());
		newCartLine.setProductId(cart.getProductId());
		newCartLine.setQty(cart.getQty());
		newCartLine.setUserStatus(resolveUserStatus());
		newCartLine.setCartStatus(getCartStatus());
		newCartLine.setCartInceptionTime(getCartInceptionDate());
		cartRepository.save(newCartLine);
		BeanUtils.copyProperties(newCartLine, cart);
		return cart;
	}

	private CartVO updateExistingCartProducts(Cart currentCartProduct, CartVO cart) {
		currentCartProduct.setQty(cart.getQty());
		currentCartProduct.setUserStatus(resolveUserStatus());
		currentCartProduct.setCartStatus(getCartStatus());
		currentCartProduct.setCartInceptionTime(getCartInceptionDate());
		cartRepository.save(currentCartProduct);
		BeanUtils.copyProperties(currentCartProduct, cart);
		return cart;
	}

	private CartVO addprodutsToExistingCart(Cart currentCartProduct, CartVO cart) {
		List<Cart> customerCarts = cartRepository.getCustomerCart(cart.getSessionId());
		Cart addNewProduct = new Cart();
		addNewProduct.setSessionId(cart.getSessionId());
		addNewProduct.setCartId(customerCarts.get(0).getCartId());
		addNewProduct.setProductId(cart.getProductId());
		addNewProduct.setQty(cart.getQty());
		addNewProduct.setUserStatus(resolveUserStatus());
		addNewProduct.setCartStatus(getCartStatus());
		addNewProduct.setCartInceptionTime(getCartInceptionDate());

		cartRepository.save(addNewProduct);
		BeanUtils.copyProperties(addNewProduct, cart);
		return cart;
	}

	// TODO move this to a helper utility, keep code aligned to what and not how
	private Integer getNewCartAggID() {

		return (cartRepository.getNeCartAggID() != null ? cartRepository.getNeCartAggID() + 1 : 1000);
	}

	// move to static constants
	private String resolveUserStatus() {

		return "GUEST_USER";
	}

	private String getCartStatus() {

		return "IN_CART";
	}

	private Date getCartInceptionDate() {

		return new Date();
	}

}
