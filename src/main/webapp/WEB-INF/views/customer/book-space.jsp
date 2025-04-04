<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <title>Book Space</title>
</head>
<body>
<h1>Book a Space</h1>
<form th:action="@{/customer/book}" th:object="${reservation}" method="post">
  <input type="hidden" th:field="*{space.spaceID}">
  Your Name: <input type="text" th:field="*{customerName}" required><br>
  Date: <input type="date" th:field="*{date}" required><br>
  Start Time: <input type="time" th:field="*{startTime}" required><br>
  End Time: <input type="time" th:field="*{endTime}" required><br>
  <button type="submit">Confirm Booking</button>
</form>
<a href="/customer/panel">Back to Customer Panel</a>
</body>
</html>