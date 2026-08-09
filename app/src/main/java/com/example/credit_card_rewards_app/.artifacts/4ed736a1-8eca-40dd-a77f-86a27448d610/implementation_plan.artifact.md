# Fix KSP Error in RewardNames_Dao

The KSP error `Type of the parameter must be a class annotated with @Entity or a collection/array of it` is caused by the `insertName(name: String)` method in `RewardNames_Dao.kt`. Room's `@Insert` annotation requires the parameter to be an entity class, but `String` is not an entity.

## Proposed Changes

### [Component: Database]

#### [MODIFY] [RewardNames_Dao.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/RewardNames_Dao.kt)

- Remove the invalid `insertName(name: String)` method which is causing the KSP error.
- The `insertNames(rewardNames: RewardNames)` method already exists and is correctly used in `AppDatabase.kt`.

## Verification Plan

### Automated Tests
- Run `./gradlew assembleDebug` to verify that the KSP error is resolved and the project builds successfully.

### Manual Verification
- None required as this is a build-time fix.
