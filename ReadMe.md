# Book My Stay - Hotel Management System

A Java-based hotel reservation backend that demonstrates the use of Inheritance, Polymorphism, and centralized inventory management. This project is structured to show the evolution from basic class structures to complex data mapping.

## 🏨 Project Overview

**Book My Stay** is a simplified hotel management system that handles different types of room categories (Single, Double, Suite) and manages their availability dynamically using Java collections.

## 📂 Repository Roadmap

The project is divided into specific use case branches to showcase technical growth:

- **main**: Documentation and project overview.
- **uc1**: **Entry Point** - Basic setup and welcome application logic.
- **uc2**: **Inheritance & Abstraction** - Implementation of the `Room` base class and specialized subclasses (`SingleRoom`, `DoubleRoom`, `SuiteRoom`) using static availability tracking.
- **uc3**: **Inventory Management** - Introduction of the `RoomInventory` class using `HashMap<String, Integer>` to centralize and manage room counts efficiently.

## 🛠️ Technical Features

- **Inheritance & Polymorphism**: specialized room types inherit common attributes from a base `Room` class.
- **Static Context**: Used in early stages to track global availability across class instances.
- **Collection Framework**: Utilizes `HashMap` for $O(1)$ lookup and update of room inventory.
- **Centralized Logic**: Separates the "Main" application logic from the "Data" (Inventory) logic.



## 🏗️ Class Architecture

- `Book_my_stay_app.java`: The main driver class and user interface.
- `Room.java`: The abstract base class containing shared properties like `roomType` and `price`.
- `SingleRoom.java` / `DoubleRoom.java` / `SuiteRoom.java`: Subclasses that define specific behaviors and costs.
- `RoomInventory.java`: The management layer that holds the state of the hotel's availability.

## 🚀 Getting Started

1. **Clone the repo:**
   ```powershell
   git clone [https://github.com/rpstud/Book_My_Stay_App.git](https://github.com/rpstud/Book_My_Stay_App.git)
