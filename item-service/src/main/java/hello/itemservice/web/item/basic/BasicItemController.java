package hello.itemservice.web.item.basic;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // final 이 붙은 멤버변수만 사용해서 생성자를 자동으로 만들어준다.
public class BasicItemController {
	
	private final ItemRepository itemRepository;
	
	@GetMapping
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items",items);
		return "basic/items";
	}
	
	// PathVariable 로 넘어온 상품ID로 상품을 조회하고, 모델에 담아둔다. 그리고 뷰 템플릿을 호출한다
	@GetMapping("/{userId}")
	public String item(@PathVariable Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/item";
	}
	
	@GetMapping("/add")
	public String addForm(@PathVariable Long itemId, Model model) {
		return "basic/addForm";
	}
	
	// @PostMapping("/add")
	public String addItemV1(@RequestParam String itemName,
							@RequestParam int price,
							@RequestParam Integer quantity,
							Model model) {
		Item item = new Item();
			 item.setItemName(itemName);
			 item.setPrice(price);
			 item.setQuantity(quantity);
			 
		itemRepository.save(item);
		
		model.addAttribute("item", item);
		
		return "basic/item";
	}

	/**
	 * @ModelAttribute 
	 *  1) Item 객체를 생성하고, 요청 파라미터의 값을 프로퍼티 접근법(setXxx)으로 입력해준다.
	 *  2) 모델(Model)에 @ModelAttribute로 지정한 객체를 자동으로 넣어준다. 
	 *     지금 코드를 보면 model.addAttribute("item", item) 가 주석처리
		      되어 있어도 잘 동작하는 것을 확인할 수 있다
	 * */
	// @PostMapping("/add")
	public String addItemV2(@ModelAttribute Item item, Model model) {
		itemRepository.save(item);
		
		// model.addAttribute("item", item); // 자동 추가, 생략 가능
		
		return "basic/item";
	}
	
	/**
	 * @ModelAttribute name 생략 가능
	 *   model.addAttribute(item); 자동 추가, 생략 가능
	 *   생략시 model에 저장되는 name은 클래스명 첫글자만 소문자로 등록 Item -> item
	 */
	// @PostMapping("/add")
	public String addItemV3(@ModelAttribute Item item) {
		itemRepository.save(item);
		return "basic/item";
	}
	
	/**
	 * @ModelAttribute 자체 생략 가능
     * model.addAttribute(item) 자동 추가
     * 수정한 뒤 다시 입력화면으로 넘어가면 이전 값이 계속 저장되는 문제가 발생 
     * 따라서, addItemV6와 같이 저장 후 리스트 페이지로 redirect 처리한다.
	 * */
	// @PostMapping("/add")
	public String addItemV4(Item item) {
		itemRepository.save(item);
		return "basic/item";
	}
	
	@PostMapping("/add")
	public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
		Item savedItem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", savedItem.getId());
		redirectAttributes.addAttribute("status", true);
		return "redirect:/basic/items/{itemId}";
	}
	
	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/editForm";
	}
	
	/**
	 * redirect:/basic/items/{itemId}
	 *  - 컨트롤러에 매핑된 @PathVariable 의 값은 redirect 에도 사용 할 수 있다
	 *  - redirect:/basic/items/{itemId} {itemId} 는 
	 *    @PathVariable Long itemId 의 값을 그대로 사용한다
	 * */
	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
		itemRepository.update(itemId, item);
		return "redirect:/basic/items/{itemId}";
	}
	
	/**
	 * 테스트용 데이터 추가
	 * */
	@PostConstruct // @PostConstruct : 해당 빈의 의존관계가 모두 주입되고 나면 초기화 용도로 호출된다.
	public void init() {
		itemRepository.save(new Item("testA",10000,10));
		itemRepository.save(new Item("testB",20000,20));
	}
}
