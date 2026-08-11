# Logic-First Cleanup and UI Simplification

This plan focuses on refining the data flow and naming conventions across the `BestReward`, `AddCard`, and `UpdateCard` features, while simplifying the UI to focus on core logic.

## User Review Required

> [!IMPORTANT]
> I will be simplifying the UI of `BestReward` back to a basic list while keeping the improved `selectedCategory` logic. I will also consolidate multiple database operations into single ViewModel actions (e.g., `saveChanges()`) and rename methods to be more event-driven (e.g., `onNameChange`).

## Proposed Changes

### [Component: Logic & Naming]

#### [MODIFY] [AddCardViewModel.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/AddCardViewModel.kt)
- Rename `addCardName` to `onNameChange`.
- Rename `addCardandRewards` to `saveCard`.
- Rename `updateRewardMap` to `onRewardValueChange`.

#### [MODIFY] [UpdateCardViewModel.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/UpdateCardViewModel.kt)
- Rename `updateCardName` to `onNameChange`.
- Rename `fillUpdateRewardMap` to `onRewardValueChange`.
- Consolidate `insertUpdatedCard` and `updateRewards` into a single `saveChanges()` method.
- Rename `deleteCardandRewards` to `deleteCard`.

#### [MODIFY] [BestReward.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/BestReward.kt)
- Simplify UI to a basic `Column` with `Button`s and `Text` while retaining the `selectedCategory` and `recommendedCards` logic.

### [Component: Screens]

#### [MODIFY] [AddCard.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/AddCard.kt)
- Update calls to renamed ViewModel methods.

#### [MODIFY] [UpdateCard.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/UpdateCard.kt)
- Update calls to renamed ViewModel methods.
- Simplify button logic to use the consolidated `saveChanges()` method.

## Verification Plan

### Automated Tests
- Run `./gradlew assembleDebug` to ensure all renames are consistent and the project builds.

### Manual Verification
- Verify that saving and updating cards still works correctly with the consolidated methods.
- Verify that the `BestReward` screen correctly shows recommendations for the selected category in a simplified layout.
