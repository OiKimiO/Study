package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;
    private final ItemValidator itemValidator;
    
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }
/*
    @PostMapping("/add")
    public String addItemV1(@ModelAttribute Item item, 
				    		BindingResult bindingResult, 
				    		RedirectAttributes redirectAttributes) {
        
    	// 검증 로직
    	if(!StringUtils.hasText(item.getItemName())) {
    		bindingResult.addError(new FieldError("item","itemName","상품 이름은 필수입니다."));
    	}
    	
    	if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
    		bindingResult.addError(new FieldError("item","price","가격은 1,000 ~ 1,000,000까지 허용합니다."));
    	}
    	
    	if(item.getQuantity() == null || item.getQuantity() >= 9999) {
    		bindingResult.addError(new FieldError("item","quantity","수량은 최대 9,999까지 허용합니다."));
    	}
    	
    	// 특정 필드가 아닌 복합 룰 검증
    	if(item.getPrice() != null && item.getQuantity() != null) {
    		int resultPrice = item.getPrice() * item.getQuantity();
    		if(resultPrice < 10000) {
    			bindingResult.addError(new ObjectError("item","가격 X 수량의 합은 10,000원 이상이어야 합니다. 현재값 = "+resultPrice));
    		}
    	}
    	
    	// 검증에 실패하면 다시 입력 폼으로
    	if(bindingResult.hasErrors()) {
    		log.info("error={}",bindingResult);
    		return "validation/v2/addForm";
    	}
    	
    	// 성공로직
    	Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }*/
    
    /*
    @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, 
				    		BindingResult bindingResult, 
				    		RedirectAttributes redirectAttributes) {
        
    	// 검증 로직
    	if(!StringUtils.hasText(item.getItemName())) {
    		bindingResult.addError(new FieldError("item","itemName",item.getItemName(),false, null, null, "상품 이름은 필수입니다."));
    	}
    	
    	if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
    		bindingResult.addError(new FieldError("item","price",item.getPrice(),false, null, null, "가격은 1,000 ~ 1,000,000까지 허용합니다."));
    	}
    	
    	if(item.getQuantity() == null || item.getQuantity() >= 9999) {
    		bindingResult.addError(new FieldError("item","quantity",item.getQuantity(), false, null, null, "수량은 최대 9,999까지 허용합니다."));
    	}
    	
    	// 특정 필드가 아닌 복합 룰 검증
    	if(item.getPrice() != null && item.getQuantity() != null) {
    		int resultPrice = item.getPrice() * item.getQuantity();
    		if(resultPrice < 10000) {
    			bindingResult.addError(new ObjectError("item",null,null,"가격 X 수량의 합은 10,000원 이상이어야 합니다. 현재값 = "+resultPrice));
    		}
    	}
    	
    	// 검증에 실패하면 다시 입력 폼으로
    	if(bindingResult.hasErrors()) {
    		log.info("error={}",bindingResult);
    		return "validation/v2/addForm";
    	}
    	
    	// 성공로직
    	Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }*/
    
    /* BindingResult 는 Model에 자동으로 포함 */ 
    /*
    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, 
				    		BindingResult bindingResult, 
				    		RedirectAttributes redirectAttributes) {
        
    	// 검증 로직
    	// 상품명이 존재하는지의 여부를 물음
    	if(!StringUtils.hasText(item.getItemName())) {
    		bindingResult.addError(
    				new FieldError("item",
    							   "itemName",
    							   item.getItemName(),
    							   false, 
    							   new String[] {"required.item.itemName"}, 
    							   null, 
    							   null)
    				);
    	}
    	
    	// 1000원 미만 1,000,000원 초과 금액은 받지 않음 
    	if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
    		bindingResult.addError(
    				new FieldError("item",
    							   "price",
    							   item.getPrice(),
    							   false, 
    							   new String[] {"range.item.price"}, 
    							   new Object[] {1000,10000000}, 
    							   null)
    				);
    	}
    	
    	// 물품 수량은 9999를 넘을 수 없음
    	if(item.getQuantity() == null || item.getQuantity() >= 9999) {
    		bindingResult.addError(
    				new FieldError("item",
    							   "quantity",
    							   item.getQuantity(), 
    							   false, 
    							   new String[] {"max.item.quantity"}, 
    							   new Object[] {9999}, 
    							   null));
    	}
    	
    	// 특정 필드가 아닌 복합 룰 검증
    	if(item.getPrice() != null && item.getQuantity() != null) {
    		int resultPrice = item.getPrice() * item.getQuantity();
    		if(resultPrice < 10000) {
    			bindingResult.addError(
    					new ObjectError("item",
    									new String[] {"totalPriceMin"},
    									new Object[] {10000,resultPrice},
    									null));
    		}
    	}
    	
    	// 검증에 실패하면 다시 입력 폼으로
    	if(bindingResult.hasErrors()) {
    		log.info("error={}",bindingResult);
    		return "validation/v2/addForm";
    	}
    	
    	// 성공로직
    	Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }
    
    @PostMapping("/add")
    public String addItemV4(@ModelAttribute Item item, 
				    		BindingResult bindingResult, 
				    		RedirectAttributes redirectAttributes) {
        
    	log.info("objectName={}",bindingResult.getObjectName());
    	log.info("target={}",bindingResult.getTarget());
    	
    	// 검증 로직
    	// 상품명이 존재하는지의 여부를 물음
    	ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "itemName", "required");
    	
    	// 1000원 미만 1,000,000원 초과 금액은 받지 않음 
    	if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
    		bindingResult.rejectValue("price","range",new Object[] {1000,10000000},null);
    	}
    	
    	// 물품 수량은 9999를 넘을 수 없음
    	if(item.getQuantity() == null || item.getQuantity() >= 9999) {
    		bindingResult.rejectValue("quantity","max",new Object[] {9999},null);
    	}
    	
    	// 특정 필드가 아닌 복합 룰 검증
    	if(item.getPrice() != null && item.getQuantity() != null) {
    		int resultPrice = item.getPrice() * item.getQuantity();
    		if(resultPrice < 10000) {
    			bindingResult.reject("totalPriceMin",new Object[] {10000,resultPrice},null);
    		}
    	}
    	
    	// 검증에 실패하면 다시 입력 폼으로
    	if(bindingResult.hasErrors()) {
    		log.info("error={}",bindingResult);
    		return "validation/v2/addForm";
    	}
    	
    	// 성공로직
    	Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }*/
    /*
    @PostMapping("/add")
    public String addItemV5(@ModelAttribute Item item, 
				    		BindingResult bindingResult, 
				    		RedirectAttributes redirectAttributes) {
        
    	// Spring bean Validator를 통한 검증
    	itemValidator.validate(item, bindingResult);
    	
    	// 검증에 실패하면 다시 입력 폼으로
    	if(bindingResult.hasErrors()) {
    		log.info("error={}",bindingResult);
    		return "validation/v2/addForm";
    	}
    	
    	// 성공로직
    	Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }*/
    
    /*@InitBinder 해당 컨트롤러에만 영향을 준다.*/
    @InitBinder
    public void init(WebDataBinder dataBinder) {
    	log.info("init binder {}", dataBinder);
    	dataBinder.addValidators(itemValidator);
    }
    /* addItemV6 등록 폼으로 연결을 도외주는 함수 
     *  @Validated 검증기를 실행하라는 애노테이션 
     *    - 이 애노테이션이 붙으면 앞서 WebDataBinder에 등록한 검증기를 찾아서 실행
     *      그런데 여러 검증기를 등록하면 그 중 어떤 검증기를 실행할지 구분이 필요 
     *      이때 supports()가 사용됨
     *      여기서는 supports(Item.class)가 호출되고 결과가 true임으로 ItemValidator의 validate()가  호출
     * 
     * */
    @PostMapping("/add")
    public String addItemV6(@Validated @ModelAttribute Item item, 
				    		BindingResult bindingResult, 
				    		RedirectAttributes redirectAttributes) {
        
    	// 검증에 실패하면 다시 입력 폼으로
    	if(bindingResult.hasErrors()) {
    		log.info("error={}",bindingResult);
    		return "validation/v2/addForm";
    	}
    	
    	// 성공로직
    	Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }
    
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

}

