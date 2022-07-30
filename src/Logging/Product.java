package Logging;

import Logging.Exception.NameFormatException;
import Logging.Exception.NoPropertyException;

public class Product {
    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Product(String productName) {
        if(productName == null){
            throw new NoPropertyException("이름을 적어주세요");
        }else if(productName.length() > 5){
            throw new NameFormatException("이름이 너무 깁니다 이름은 7자이내로 만들어 주세요");
        }else{
            this.productName = productName;
        }
    }
}
