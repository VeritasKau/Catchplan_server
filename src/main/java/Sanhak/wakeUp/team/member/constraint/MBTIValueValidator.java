//package Sanhak.wakeUp.team.member.constraint;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//public class MBTIValueValidator implements ConstraintValidator<ValidMBTIValue, String> {
//    @Override
//    public void initialize(ValidMBTIValue constraintAnnotation) {
//    }
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        if (value == null) {
//            return true;
//        }
//        return value.equals("ESTJ") || value.equals("ISTJ") ||  value.equals("ESFP") || value.equals("ISFP") || value.equals("ESTP") || value.equals("ISTP") ||  value.equals("ESFJ") || value.equals("ISFJ")
//                || value.equals("ENTJ") || value.equals("INTJ") ||  value.equals("ENFP") || value.equals("INFP") || value.equals("ENTP") || value.equals("INTP") ||  value.equals("ENFJ") || value.equals("INFJ")
//                || value.equals("estj") || value.equals("istj") ||  value.equals("esfp") || value.equals("isfp") || value.equals("estp") || value.equals("istp") ||  value.equals("esfj") || value.equals("isfj")
//                || value.equals("entj") || value.equals("intj") ||  value.equals("enfp") || value.equals("infp") || value.equals("entp") || value.equals("intp") ||  value.equals("enfj") || value.equals("infj");
//    }
//}