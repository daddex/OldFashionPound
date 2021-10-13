package org.daddex.oldfashionpound;

import org.daddex.oldfashionpound.business.IOperationStrategy;
import org.daddex.oldfashionpound.business.OperationLookUp;


public class CurrencyApp {
    public static void main(String[] args) {

        OperationLookUp operationLookUp =  new OperationLookUp();

        String element =   operationLookUp.convertVerifyInput(args);

        System.out.println(String.format("input is  = %s", element));
        IOperationStrategy operationStrategy = operationLookUp.getOperation(element);
        String result = operationStrategy.doOperation(element);

    }
}
