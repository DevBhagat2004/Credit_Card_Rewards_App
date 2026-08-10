# Fix State Management and Side Effects

This plan addresses logic flaws related to Room/Compose state management and improper side-effect handling across multiple screens.

## User Review Required

> [!IMPORTANT]
> I will be consolidating data initialization into ViewModel functions and ensuring all state updates create new object instances to ensure Compose recomposition works correctly.

## Proposed Changes

### [Component: Screens & ViewModels]

#### [MODIFY] [AddCard.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/AddCard.kt)
- Consolidate `getRewardNames()` and `makeRewardMap()` into a single `initialize()` call in `LaunchedEffect(Unit)`.
- Ensure navigation to `HomeScreen` only happens after a successful save.

#### [MODIFY] [AddCardViewModel.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/AddCardViewModel.kt)
- Combine `getRewardNames` and `makeRewardMap` into `initializeData()`.
- Add safe double parsing in `addCardandRewards` to prevent crashes on invalid input.

#### [MODIFY] [UpdateCard.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/UpdateCard.kt)
- Move `getCard(cardId)` and `getRewards(cardId)` into `LaunchedEffect(cardId)`.
- Fix `TextField` for reward values:
    - Display user input from `toUpdateRewardMap` if present.
    - Use `toMutableMap()` in `onValueChange` to ensure state updates trigger recomposition.
- Navigate back after Update/Delete.

#### [MODIFY] [UpdateCardViewModel.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/UpdateCardViewModel.kt)
- Optimize `updateRewards` to use a single coroutine.
- Add safe double parsing.

#### [MODIFY] [ShowCard.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/ShowCard.kt)
- Move `showCard()` call to `LaunchedEffect(Unit)`.
- Implement navigation to `UpdateCard` when a card is clicked.

#### [MODIFY] [MainActivity.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/MainActivity.kt)
- Add missing routes for `UpdateCard` and `BestReward`.

#### [MODIFY] [HomeScreen.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/HomeScreen.kt)
- Implement `onClick` handlers for menu buttons.

## Verification Plan

### Automated Tests
- Build the project to ensure no compilation errors.
- I will check if I can add a basic unit test for the ViewModel state logic if time permits.

### Manual Verification
- Deploy to a device/emulator.
- Verify that typing in `AddCard` and `UpdateCard` updates the UI immediately.
- Verify that navigating between screens works as expected.
- Verify that invalid input (e.g., "abc" for a reward value) doesn't crash the app.
