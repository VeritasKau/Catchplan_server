//package Sanhak.wakeUp.team.member.constraint;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//public class SexValueValidator implements ConstraintValidator<ValidSexValue, String> {
//    @Override
//    public void initialize(ValidSexValue constraintAnnotation) {
//    }
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        if (value == null) {
//            return true;
//        }
//        return value.equals("M") || value.equals("F") || value.equals("m") || value.equals("f");
//    }
//}
