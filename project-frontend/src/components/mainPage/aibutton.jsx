import React from "react";

export default function AiSidebar({ isOpen, onClose }) {
  return (
    <div
      className={`fixed top-0 right-0 h-full w-80 bg-white shadow-lg transform transition-transform duration-300 ${
        isOpen ? "translate-x-0" : "translate-x-full"
      }`}
    >
      {/* Header */}
      <div className="flex justify-between items-center p-4 border-b">
        <h2 className="text-lg font-bold">AI Assistant</h2>
        <button onClick={onClose} className="text-gray-600 hover:text-gray-800">
          ✖
        </button>
      </div>

      {/* Body */}
      <div className="p-4 flex flex-col gap-4">
        {/* Input */}
        <input
          type="text"
          placeholder="Ask me anything..."
          className="border rounded px-3 py-2 w-full focus:outline-none focus:ring focus:ring-blue-400"
        />

        {/* Upload Button */}
        <button className="bg-sky-900 text-white px-4 py-2 rounded hover:bg-blue-700">
          Upload
        </button>
      </div>
    </div>
  );
}
