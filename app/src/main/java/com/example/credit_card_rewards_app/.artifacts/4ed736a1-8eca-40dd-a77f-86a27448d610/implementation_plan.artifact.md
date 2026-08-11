# Simple and Clean UI Refinement

This plan aims to improve the app's UI to be clean and professional while strictly adhering to the "not too fancy" and "short code" constraints. We will use standard Material 3 components to provide structure and consistent spacing.

## User Review Required

> [!NOTE]
> I will be introducing `Scaffold` with a `TopAppBar` across all screens to provide a consistent navigation header.
> I will also use `OutlinedTextField` and proper spacing (`padding`, `spacedBy`) to improve readability.

## Proposed Changes

### [Component: UI Refinement]

#### [MODIFY] [HomeScreen.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/HomeScreen.kt)
- Wrap in `Scaffold` with "Credit Card Rewards" title.
- Center buttons vertically and horizontally.
- Use `ElevatedButton` for a slightly more tactile feel.

#### [MODIFY] [AddCard.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/AddCard.kt)
- Use `Scaffold` with "Add New Card" title.
- Use `OutlinedTextField` with `keyboardType = KeyboardType.Decimal` for reward values.
- Improve spacing using `Arrangement.spacedBy`.

#### [MODIFY] [ShowCard.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/ShowCard.kt)
- Use `Scaffold` with "My Cards" title.
- Use `LazyColumn` with `ElevatedCard` for each card entry.

#### [MODIFY] [UpdateCard.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/UpdateCard.kt)
- Use `Scaffold` with "Update Card" title.
- Mirror `AddCard` improvements for input fields.
- Add an icon to the Delete button for clarity.

#### [MODIFY] [BestReward.kt](file:///C:/Users/bhaga/AndroidStudioProjects/Credit_Card_Rewards_App/app/src/main/java/com/example/credit_card_rewards_app/BestReward.kt)
- Use `Scaffold` with "Best Rewards" title.
- Organize categories in a flow or structured list.
- Use `ElevatedCard` for recommended cards.

## Verification Plan

### Automated Tests
- Run `./gradlew assembleDebug` to ensure all imports (especially M3) are correct.

### Manual Verification
- Deploy to device/emulator.
- Verify that each screen has a consistent header and better spacing.
- Check that the numeric keyboard opens correctly for reward values.
