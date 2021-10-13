package org.daddex.oldfashionpound.utils;

import org.daddex.oldfashionpound.business.*;
import org.daddex.oldfashionpound.exception.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUtils {
    @Test
    public void transcodeOperation() {
        TranscoderUtil.OperationEnum operationEnum = TranscoderUtil.fromType("+");
        assertEquals(operationEnum, TranscoderUtil.OperationEnum.OPERATION_ADDITION);

        operationEnum = TranscoderUtil.fromType("*");
        assertEquals(operationEnum, TranscoderUtil.OperationEnum.OPERATION_MULTIPLICATION);

        operationEnum = TranscoderUtil.fromType("-");
        assertEquals(operationEnum, TranscoderUtil.OperationEnum.OPERATION_SUBTRACTION);

        operationEnum = TranscoderUtil.fromType("/");
        assertEquals(operationEnum, TranscoderUtil.OperationEnum.OPERATION_DIVISION);

        operationEnum = TranscoderUtil.fromType(null);
        assertEquals(operationEnum, TranscoderUtil.OperationEnum.OPERATION_UNRECOGNIZED);

        operationEnum = TranscoderUtil.fromType("null");
        assertEquals(operationEnum, TranscoderUtil.OperationEnum.OPERATION_UNRECOGNIZED);
    }

    @Test
    public void verifyLookUp() {
        OperationLookUp operationLookUp = new OperationLookUp();

        assertTrue(operationLookUp.getOperation("5p 17s 8d + 3p 4s 10d") instanceof OperationAddition);

        assertTrue(operationLookUp.getOperation("5p 17s 8d - 3p 4s 10d") instanceof OperationSubtract);

        assertTrue(operationLookUp.getOperation("5p 17s 8d - 3p 4s 10d") instanceof OperationSubtract);

        assertTrue(operationLookUp.getOperation(" 5p 17s 8d * 2") instanceof OperationMultiply);


        assertTrue(operationLookUp.getOperation("5p 17s 8d / 3") instanceof OperationDivision);
    }


    @Test
    void Should_ThrowException_When_Input_Has_Less_Element() {
        Assertions.assertThrows(BadInputException.class, () -> {
            String[] args = {"5p", "17s", "-", "6"};
            OperationLookUp operationLookUp = new OperationLookUp();
            operationLookUp.convertVerifyInput(args);
        });
    }

    @Test
    void Should_ThrowException_When_Operation_is_Not_Supported() {
        Assertions.assertThrows(OperationNotRecognizedException.class, () -> {
            OperationLookUp operationLookUp = new OperationLookUp();
            String element = "5p 17s 8d # 5p 17s 8d";
            IOperationStrategy operationStrategy = operationLookUp.getOperation(element);
            String result = operationStrategy.doOperation(element);
        });
    }

    @Test
    void Should_ThrowException_When_Addition_Has_Less_Elements() {
        Assertions.assertThrows(BadAdditionInputException.class, () -> {
            OperationLookUp operationLookUp = new OperationLookUp();
            String element = "5p 17s 8d + 17x";
            IOperationStrategy operationStrategy = operationLookUp.getOperation(element);
            String result = operationStrategy.doOperation(element);
        });
        Assertions.assertThrows(BadAdditionInputException.class, () -> {
            OperationLookUp operationLookUp = new OperationLookUp();
            String element = "5p 17s 8d + 17";
            IOperationStrategy operationStrategy = operationLookUp.getOperation(element);
            String result = operationStrategy.doOperation(element);
        });
    }

    @Test
    void Should_ThrowException_When_Subtraction_Has_Less_Elements() {
        Assertions.assertThrows(BadSubtractionInputException.class, () -> {
            OperationLookUp operationLookUp = new OperationLookUp();
            String element = "5p 17s 8d - 17";
            IOperationStrategy operationStrategy = operationLookUp.getOperation(element);
            String result = operationStrategy.doOperation(element);
        });
    }

    @Test
    void Should_ThrowException_When_Multiply_Has_Bad_Input() {
        Assertions.assertThrows(BadMultiplyInputException.class, () -> {
            OperationLookUp operationLookUp = new OperationLookUp();
            String element = "5p 17s 8d * 17x";
            IOperationStrategy operationStrategy = operationLookUp.getOperation(element);
            String result = operationStrategy.doOperation(element);
        });
    }

    @Test
    void Should_ThrowException_When_Division_Has_Bad_Input() {
        Assertions.assertThrows(BadDivisionInputException.class, () -> {
            OperationLookUp operationLookUp = new OperationLookUp();
            String element = "5p 17s 8d / 17x";
            IOperationStrategy operationStrategy = operationLookUp.getOperation(element);
            String result = operationStrategy.doOperation(element);
        });
        Assertions.assertThrows(BadDivisionInputException.class, () -> {
            OperationLookUp operationLookUp = new OperationLookUp();
            String element = "5p 17s 8d / 0";
            IOperationStrategy operationStrategy = operationLookUp.getOperation(element);
            String result = operationStrategy.doOperation(element);
        });
    }

    @Test
    void Should_ThrowException_When_AmountExceed() {
        Assertions.assertThrows(AmountExceededException.class, () -> {
            OperationLookUp operationLookUp = new OperationLookUp();
            String element = "5p 17s 8d + 5p 21s 8d";
            IOperationStrategy operationStrategy = operationLookUp.getOperation(element);
            String result = operationStrategy.doOperation(element);
        });
        Assertions.assertThrows(AmountExceededException.class, () -> {
            OperationLookUp operationLookUp = new OperationLookUp();
            String element = "5p 20s 8d - 5p 8s 8d";
            IOperationStrategy operationStrategy = operationLookUp.getOperation(element);
            String result = operationStrategy.doOperation(element);
        });
    }

    @Test
    void When_Valid_operation_Are_Passed() {
        OperationLookUp operationLookUp = new OperationLookUp();
        String element = "5p 17s 8d + 3p 4s 10d";
        IOperationStrategy operationStrategy = operationLookUp.getOperation(element);
        String result = operationStrategy.doOperation(element);
        assertEquals(result, "9p 2s 6d");

        element = "5p 17s 8d - 3p 4s 10d";
        operationStrategy = operationLookUp.getOperation(element);
        result = operationStrategy.doOperation(element);
        assertEquals(result, "2p 12s 10d");

        element = "5p 17s 8d * 2";
        operationStrategy = operationLookUp.getOperation(element);
        result = operationStrategy.doOperation(element);
        assertEquals(result, "11p 15s 4d");

        element = "18p 16s 1d / 15";
        operationStrategy = operationLookUp.getOperation(element);
        result = operationStrategy.doOperation(element);
        assertEquals(result, "1p 5s 0d (1s 1d)");


    }

}
