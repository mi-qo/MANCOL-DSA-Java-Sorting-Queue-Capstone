# Apparel Brand Order Processing System

## Overview
This is a procedural Java application designed to manage and process customer orders for a clothing brand. It strictly utilizes standard procedural programming concepts without custom objects or OOP classes. The system leverages parallel `ArrayList` structures for data storage and a `LinkedList` (FIFO Queue) for order processing.

## Core Features
* **Order Management:** Add new apparel orders with unique IDs, customer names, items, and total amounts.
* **Input Validation:** Prevents application crashes by catching invalid numeric inputs (e.g., entering letters for the total amount).
* **Queue Operations (FIFO):**
  * **Enqueue:** New orders are added to the back of the processing line.
  * **Dequeue:** Processes and ships the order currently at the front of the line.
  * **Peek:** Views the next order in line without removing it.
* **Manual Sorting Algorithms:**
  * **Bubble Sort:** Sorts the entire order database by Total Amount in descending order (highest value first).
  * **Insertion Sort:** Sorts the order database alphabetically by Customer Name.

## Technical Requirements Met
* Language: Java
* Data Structures: `ArrayList`, `Queue`, `LinkedList`
* Paradigm: Procedural Programming (No OOP)

## System Screenshots
*(Reference the `screenshots/` folder for visual proof of operations)*
1. `add-display.png` - Adding records and displaying the parallel lists.
2. `input-validation.png` - Error handling for invalid price inputs.
3. `queue-operations.png` - Peeking and Dequeuing from the FIFO line.
4. `bubble-sort.png` - Data sorted by total amount (descending).
5. `insertion-sort.png` - Data sorted alphabetically by customer name.