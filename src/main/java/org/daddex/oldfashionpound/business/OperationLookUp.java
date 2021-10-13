package org.daddex.oldfashionpound.business;

import org.daddex.oldfashionpound.exception.BadInputException;
import org.daddex.oldfashionpound.exception.OperationNotRecognizedException;
import org.daddex.oldfashionpound.utils.TranscoderUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.daddex.oldfashionpound.utils.AppConstant.BAD_OPERATION_ERROR_MESSAGE;
import static org.daddex.oldfashionpound.utils.AppConstant.NOT_VALID_INPUT_ERROR_MESSAGE;
import static org.daddex.oldfashionpound.utils.TranscoderUtil.extractAlgebraicOperator;

public class OperationLookUp {

    public String convertVerifyInput(String[] args){
        if (args.length != 1 ) {
            System.out.println(" ampiezza argomenti "+args.length);
            throw new BadInputException(
                    String.format(NOT_VALID_INPUT_ERROR_MESSAGE), Arrays.toString(args));
        }
        args = args[0].split(" ");
        if (args.length < 5 || args.length > 7) {
            throw new BadInputException(
                    String.format(NOT_VALID_INPUT_ERROR_MESSAGE), Arrays.toString(args));
        }
        List<String> elementAsList = Arrays.asList(args);
        return elementAsList.stream().collect(Collectors.joining(" "));
    }
   public IOperationStrategy getOperation(String elements){
        String operation = extractAlgebraicOperator(elements);
        TranscoderUtil.OperationEnum operationEnum =TranscoderUtil.fromType(operation);
        switch (operationEnum){
            case  OPERATION_ADDITION:
                return new OperationAddition(operationEnum.getAppliedPattern());
            case  OPERATION_SUBTRACTION:
                return new OperationSubtract(operationEnum.getAppliedPattern());
            case  OPERATION_MULTIPLICATION:
                return new OperationMultiply(operationEnum.getAppliedPattern());
            case  OPERATION_DIVISION:
                return new OperationDivision(operationEnum.getAppliedPattern());
            case  OPERATION_UNRECOGNIZED:
                throw new OperationNotRecognizedException(BAD_OPERATION_ERROR_MESSAGE,elements);
        }
       throw new OperationNotRecognizedException(BAD_OPERATION_ERROR_MESSAGE,elements);
    }
}
