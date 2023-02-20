package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;

@Data
public class ItemV1 {

	@NotNull(groups=UpdateCheck.class) // 수정시 요구사항 추가
    private Long id;
    
    /* @NotBlank : 빈값 + 공백만 있는 경우 허용하지 않음 */
    @NotBlank(groups= {SaveCheck.class,
    				   UpdateCheck.class})
    private String itemName;
    
    /* @NotNull : null을 허용하지 않음 
     * @Range(min=1000,max=1000000): 범위 안의 값이어야 한다.
     * */
    @NotNull(groups = {SaveCheck.class,
    				   UpdateCheck.class})
    @Range(min=1000,max=1000000,groups= {SaveCheck.class,UpdateCheck.class})
    private Integer price;
    
    /* @Max(9999) : 최대 9999까지만 허용한다.
     * */
    @NotNull(groups= {SaveCheck.class,
			   		  UpdateCheck.class})
    @Max(value=9999, groups=SaveCheck.class) // 수정시 요구사항 추가
    private Integer quantity;

    public ItemV1() {
    }

    public ItemV1(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
