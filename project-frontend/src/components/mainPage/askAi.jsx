import React, { useState } from "react";

export default function AskAi() {
  const [goal, setGoal] = useState("");
  const [loading, setLoading] = useState(false);
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!goal.trim()) return;

    setLoading(true);
    setError(null);
    setResult(null);

    try {
      const response = await fetch(
        `http://localhost:9090/api/exercise/recommendations?goal=${encodeURIComponent(goal)}`
      );

      if (!response.ok) {
        throw new Error("Failed to fetch recommendations.");
      }

      const data = await response.json();
      setResult(data); // assuming API returns { name, benefits, steps }
    } catch (err) {
      setError(err.message || "Something went wrong.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="p-6 max-w-2xl mx-auto">
      <h2 className="text-2xl font-bold text-gray-800 mb-4">
        Ask AI for Fitness Recommendations
      </h2>

      {/* Input Form */}
      <form
        onSubmit={handleSubmit}
        className="flex gap-3 mb-6 bg-gray-100 p-4 rounded-lg shadow"
      >
        <input
          type="text"
          value={goal}
          onChange={(e) => setGoal(e.target.value)}
          placeholder="Enter your fitness goal (e.g., build muscle, lose weight)..."
          className="flex-1 p-2 border rounded-md focus:ring-2 focus:ring-blue-500 outline-none"
        />
        <button
          type="submit"
          className="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700 transition"
          disabled={loading}
        >
          {loading ? "Loading..." : "Submit"}
        </button>
      </form>

      {/* Error Message */}
      {error && <p className="text-red-500 mb-4">{error}</p>}

      {/* Result Display */}
      {result && (
        <div className="bg-white shadow-md p-5 rounded-lg">
          <h3 className="text-xl font-semibold text-gray-900 mb-2">
            {result.name}
          </h3>
          <p className="text-gray-700 mb-3">
            <span className="font-bold">Benefits:</span> {result.benefits}
          </p>
          <div>
            <h4 className="font-bold mb-1">Steps:</h4>
            <ul className="list-disc list-inside text-gray-700 space-y-1">
              {Array.isArray(result.steps) ? (
                result.steps.map((step, index) => (
                  <li key={index}>{step}</li>
                ))
              ) : (
                <li>{result.steps}</li>
              )}
            </ul>
          </div>
        </div>
      )}
    </div>
  );
}
