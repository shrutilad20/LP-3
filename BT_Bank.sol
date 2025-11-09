// SPDX-License-Identifier: MIT
pragma solidity ^0.8.17;

/// @title Simple bank account per-user contract (single contract, many users)
/// @notice Users can deposit ETH, withdraw up to their balance, and view balances
/// @dev Uses events, checks, and basic protections (nonReentrant not used to keep simple; for production add ReentrancyGuard)
contract BankAccount {
    // mapping of user address => balance in wei
    mapping(address => uint256) private balances;

    // events
    event Deposit(address indexed user, uint256 amount, uint256 newBalance);
    event Withdraw(address indexed user, uint256 amount, uint256 newBalance);

    /// @notice Deposit ETH into caller's account
    /// @dev payable function, value is added to caller's balance
    function deposit() external payable {
        require(msg.value > 0, "Deposit: amount must be > 0");
        balances[msg.sender] += msg.value;
        emit Deposit(msg.sender, msg.value, balances[msg.sender]);
    }

    /// @notice Withdraw `amount` wei from caller's account
    /// @param amount amount to withdraw in wei
    function withdraw(uint256 amount) external {
        require(amount > 0, "Withdraw: amount must be > 0");
        uint256 bal = balances[msg.sender];
        require(bal >= amount, "Withdraw: insufficient balance");

        // Effects
        unchecked { balances[msg.sender] = bal - amount; }

        // Interaction
        (bool sent, ) = payable(msg.sender).call{value: amount}("");
        require(sent, "Withdraw: failed to send Ether");

        emit Withdraw(msg.sender, amount, balances[msg.sender]);
    }

    /// @notice Get balance of caller (wei)
    function getMyBalance() external view returns (uint256) {
        return balances[msg.sender];
    }

    /// @notice Get balance of an arbitrary user (public read)
    function getBalanceOf(address user) external view returns (uint256) {
        return balances[user];
    }

    /// @notice Allow contract to receive plain ETH (maps to msg.sender balance)
    receive() external payable {
        // treat this like deposit
        require(msg.value > 0, "receive: no value");
        balances[msg.sender] += msg.value;
        emit Deposit(msg.sender, msg.value, balances[msg.sender]);
    }

    /// @notice fallback function (called when calldata is non-empty or function doesn't exist)
    fallback() external payable {
        // fallback will also credit sender so they can send funds with data
        if (msg.value > 0) {
            balances[msg.sender] += msg.value;
            emit Deposit(msg.sender, msg.value, balances[msg.sender]);
        }
    }
}
