package org.daddex.oldfashionpound.business;

import org.daddex.oldfashionpound.exception.BadAdditionInputException;
import org.daddex.oldfashionpound.exception.BadMultiplyInputException;
import org.daddex.oldfashionpound.model.bean.OldUkCurrency;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static org.daddex.oldfashionpound.utils.AppConstant.MULTIPLY_INPUT_ERROR_MESSAGE;
import static org.daddex.oldfashionpound.utils.AppConstant.RESULT_DEBUG_MESSAGE;
import static org.daddex.oldfashionpound.utils.TranscoderUtil.*;
import static org.daddex.oldfashionpound.utils.TranscoderUtil.formatNumberToCurrency;

public class OperationMultiply implements  IOperationStrategy{
    private Pattern regexPattern;
    public OperationMultiply(Pattern currencyInputParser) {
        regexPattern=currencyInputParser;
    }
    @Override
    public String doOperation(String element) {
        Matcher matcher = regexPattern.matcher(element);
        if (matcher.find()) {
            Integer pounds_A =removeCharacterFromImport(matcher.group(1));
            Integer shillings_A = removeCharacterFromImport(matcher.group(2));
            Integer pence_A =  removeCharacterFromImport(matcher.group(3));
            Integer multiplier =removeCharacterFromImport(matcher.group(4));
            OldUkCurrency oldUkCurrency_A = OldUkCurrency.builder().pounds(pounds_A).shillings(shillings_A).pennies(pence_A).build();
            String result = computeMultiply(oldUkCurrency_A,multiplier);
            System.out.println(String.format(RESULT_DEBUG_MESSAGE," multiply ",result));
            return  result;

        }
        throw new BadMultiplyInputException(MULTIPLY_INPUT_ERROR_MESSAGE,element);
    }
    private String computeMultiply(OldUkCurrency oldUkCurrency_A,Integer multiplier){
        Integer pennies_A = convertInputToPennies(oldUkCurrency_A);
        Integer product = Math.multiplyExact(pennies_A,multiplier);
        OldUkCurrency oldUkCurrencyResult = convertPenniesToOutput(product);
        return formatNumberToCurrency(oldUkCurrencyResult);
    }
}
