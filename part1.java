import java.util.List;


class NutritionPlan {
    private int caloricIntake;
    private int carbohydratesRatio;
    private int proteinsRatio;
    private int fatsRatio;
    private List<String> mealPlans;
    private String fitnessGoal;
    private List<String> dietaryRestrictions;

    public void setCaloricIntake(int caloricIntake) {
        this.caloricIntake = caloricIntake;
    }

    public void setCarbohydratesRatio(int carbohydratesRatio) {
        this.carbohydratesRatio = carbohydratesRatio;
    }

    public void setProteinsRatio(int proteinsRatio) {
        this.proteinsRatio = proteinsRatio;
    }

    public void setFatsRatio(int fatsRatio) {
        this.fatsRatio = fatsRatio;
    }

    public void setMealPlans(List<String> mealPlans) {
        this.mealPlans = mealPlans;
    }

    public void setFitnessGoal(String fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    public void setDietaryRestrictions(List<String> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    @Override
    public String toString() {
        return "Fitness Goal: " + fitnessGoal + "\n" +
                "Caloric Intake: " + caloricIntake + " kcal\n" +
                "Macronutrient Ratios: Carbs - " + carbohydratesRatio + "%, Proteins - " +
                proteinsRatio + "%, Fats - " + fatsRatio + "%\n" +
                "Meal Plans: " + String.join(", ", mealPlans) + "\n" +
                "Dietary Restrictions: " + String.join(", ", dietaryRestrictions);
    }
}


interface NutritionPlanBuilder {
    void setCaloricIntake(int caloricIntake);
    void setMacronutrientRatios(int carbohydratesRatio, int proteinsRatio, int fatsRatio);
    void setMealPlans(List<String> mealPlans);
    void setFitnessGoal(String fitnessGoal);
    void setDietaryRestrictions(List<String> dietaryRestrictions);
    NutritionPlan build();
}


class WeightLossNutritionPlanBuilder implements NutritionPlanBuilder {
    private NutritionPlan nutritionPlan = new NutritionPlan();

    @Override
    public void setCaloricIntake(int caloricIntake) {
        nutritionPlan.setCaloricIntake(caloricIntake);
    }

    @Override
    public void setMacronutrientRatios(int carbohydratesRatio, int proteinsRatio, int fatsRatio) {
        nutritionPlan.setCarbohydratesRatio(carbohydratesRatio);
        nutritionPlan.setProteinsRatio(proteinsRatio);
        nutritionPlan.setFatsRatio(fatsRatio);
    }

    @Override
    public void setMealPlans(List<String> mealPlans) {
        nutritionPlan.setMealPlans(mealPlans);
    }

    @Override
    public void setFitnessGoal(String fitnessGoal) {
        nutritionPlan.setFitnessGoal(fitnessGoal);
    }

    @Override
    public void setDietaryRestrictions(List<String> dietaryRestrictions) {
        nutritionPlan.setDietaryRestrictions(dietaryRestrictions);
    }

    @Override
    public NutritionPlan build() {
        return nutritionPlan;
    }
}


class NutritionPlanDirector {
    private NutritionPlanBuilder builder;

    public void setBuilder(NutritionPlanBuilder builder) {
        this.builder = builder;
    }

    public NutritionPlan constructNutritionPlan() {
        // Construction logic goes here
        builder.setCaloricIntake(2000);
        builder.setMacronutrientRatios(40, 30, 30);
        builder.setMealPlans(List.of("Breakfast: Oatmeal", "Lunch: Grilled Chicken Salad", "Dinner: Salmon with Quinoa"));
        builder.setFitnessGoal("Weight Loss");
        builder.setDietaryRestrictions(List.of("Gluten-free", "Lactose-free"));
        return builder.build();
    }
}

public class part1 {
    public static void main(String[] args) {
        NutritionPlanDirector director = new NutritionPlanDirector();
        NutritionPlanBuilder builder = new WeightLossNutritionPlanBuilder();
        director.setBuilder(builder);
        NutritionPlan nutritionPlan = director.constructNutritionPlan();
        System.out.println(nutritionPlan);
    }
}
