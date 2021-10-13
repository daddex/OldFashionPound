package org.daddex.oldfashionpound.business;

import org.daddex.oldfashionpound.exception.BadAdditionInputException;
import org.daddex.oldfashionpound.exception.BadSubtractionInputException;
import org.daddex.oldfashionpound.model.bean.OldUkCurrency;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.daddex.oldfashionpound.utils.AppConstant.RESULT_DEBUG_MESSAGE;
import static org.daddex.oldfashionpound.utils.AppConstant.SUBTRACTION_INPUT_ERROR_MESSAGE;
import static org.daddex.oldfashionpound.utils.TranscoderUtil.*;
import static org.daddex.oldfashionpound.utils.TranscoderUtil.formatNumberToCurrency;

public class OperationSubtract implements  IOperationStrategy{
    private Pattern regexPattern;
    public OperationSubtract(Pattern currencyInputParser) {
        regexPattern=currencyInputParser;
    }
    @Override
    public String doOperation(String element) {
        Matcher matcher = regexPattern.matcher(element);
        if (matcher.find()) {
            Integer pounds_A =removeCharacterFromImport(matcher.group(1));
            Integer shillings_A = removeCharacterFromImport(matcher.group(2));
            Integer pence_A =  removeCharacterFromImport(matcher.group(3));

            Integer pounds_B =removeCharacterFromImport(matcher.group(4));
            Integer shillings_B = removeCharacterFromImport(matcher.group(5));
            Integer pence_B =  removeCharacterFromImport(matcher.group(6));

            OldUkCurrency oldUkCurrency_A = OldUkCurrency.builder().pounds(pounds_A).shillings(shillings_A).pennies(pence_A).build();
            OldUkCurrency oldUkCurrency_B = OldUkCurrency.builder().pounds(pounds_B).shillings(shillings_B).pennies(pence_B).build();
            String result = computeSubtraction(oldUkCurrency_A,oldUkCurrency_B);
            System.out.println(String.format(RESULT_DEBUG_MESSAGE," subtraction ",result));
            return  result;

        }
        throw new BadSubtractionInputException(SUBTRACTION_INPUT_ERROR_MESSAGE,element);
    }
    private String computeSubtraction(OldUkCurrency oldUkCurrency_A,OldUkCurrency oldUkCurrency_B){

        Integer pennies_A = convertInputToPennies(oldUkCurrency_A);
        Integer pennies_B = convertInputToPennies(oldUkCurrency_B);
        Integer penniesSum = Integer.sum(pennies_A, -pennies_B);
        OldUkCurrency oldUkCurrencyResult = convertPenniesToOutput(penniesSum);
        return formatNumberToCurrency(oldUkCurrencyResult);
    }
}
