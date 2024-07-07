import * as vscode from "vscode";
import * as path from "path";
import axios from "axios";

const projectNames = [
  "ANNUAL_RETURN_APP",
  "BUILDOUT_XURL",
  "OBJECT_MODELING_V2",
  "BUILDOUT_MACHINE_CODING_BIDBLAST",
  "BYTES",
  "OOP_ENCAPSULATION_JAVA",
  "BUILDOUT_MACHINE_CODING_FPL_AUCTION",
  "CODING_GAME",
  "QCALC_V2",
  "BUILDOUT_MACHINE_CODING_JUKEBOX",
  "ENCAPSULATION",
  "QCONTEST",
  "BUILDOUT_MACHINE_CODING_RESTAURANT_RATING",
  "INHERITANCE",
  "QEATS_SHARED_RESOURCES",
  "BUILDOUT_XCOMPANY",
  "INTERNALQUES",
  "QEATS_V2",
  "BUILDOUT_XMEMEJAVA",
  "MYSQL",
  "QMONEY_V2",
  "MYSQL_ADVANCED",
  "BUILDOUT_XNEWS",
  "BUILDOUT_XQUIZ",
  "OBJECT_MODELING",
];

async function fetchDashboard(
  fileName: string,
  content: string,
  projectName: string
) {
  try {
    const API_KEY = "AIzaSyCSlMKjenTjUT2o6Jb0Rz9XbMF2SXhKZt0";
    // const url =
    // "https://renderconfig-jjux.onrender.com//fetchDashboard?key=" + API_KEY;
    const url =
      "https://c20658fa-f153-4500-abf9-271667b6ec33.mock.pstmn.io/fetchDashboard?key=" +
      API_KEY;
    const requestBody = {
      fileName,
      content,
      projectName,
    };

    const response = await axios.post(url, requestBody);
    const responseData = response.data;

    console.log("Response from server:");
    console.log(responseData);

    // Create and show a webview panel
    const panel = vscode.window.createWebviewPanel(
      "dashboardPanel", // Identifies the type of the webview. Used internally
      "Dashboard", // Title of the panel displayed to the user
      vscode.ViewColumn.One, // Editor column to show the new webview panel in
      {
        enableScripts: true,
      } // Webview options
    );

    // Set HTML content for the webview
    panel.webview.html = getWebViewContent(responseData);

    vscode.window.showInformationMessage(
      "Dashboard opened in a webview panel."
    );
  } catch (error: any) {
    console.error("Error fetching dashboard:", error.message);
    vscode.window.showErrorMessage(
      "Error fetching dashboard. Check console for details."
    );
  }
}

function getWebViewContent(responseData: any) {
  // Construct HTML content for the webview
  const htmlContent = `
    <!DOCTYPE html>
    <html lang="en">
    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Dashboard</title>
      <style>
        @import url('https://fonts.googleapis.com/css2?family=Orbitron:wght@400;500;600;700;800&display=swap');

        body {
          font-family: 'Orbitron', sans-serif;
          background-color: var(--background-color);
          color: var(--text-color);
          margin: 0;
          display: flex;
          justify-content: center;
          align-items: center;
          min-height: 100vh;
          padding: 20px;
          overflow-x: hidden; /* Prevent horizontal overflow */
        }
        .container {
          width: 100%;
          max-width: 800px;
          background-color: var(--item-background-color);
          padding: 20px;
          border-radius: 10px;
          box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
          position: relative;
        }
        .dashboard-header {
          display: flex;
          justify-content: space-between;
          align-items: center;
          margin-bottom: 20px;
        }
        .dashboard-header img {
          width: 100px;
          height: auto;
          position: absolute;
          top: 20px;
          right: 20px;
        }
        h1, h2, h3 {
          font-family: 'Orbitron', sans-serif;
          font-weight: 800;
          font-size: 64px;
          line-height: 80px;
          text-align: center;
          color: var(--primary-color);
          margin-bottom: 20px;
        }
        .dashboard-item {
          background: var(--item-background-color);
          border-radius: 10px;
          box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
          margin-bottom: 20px;
          padding: 20px;
        }
        .dashboard-item label {
          font-weight: 700;
          color: var(--primary-color);
        }
        .dashboard-item span,
        .dashboard-item p {
          display: block;
          margin-top: 10px;
          font-size: 18px;
          line-height: 24px;
        }
        .dashboard-item ul {
          list-style: none;
          padding: 0;
        }
        .dashboard-item ul li {
          background: var(--list-item-background-color);
          padding: 10px;
          border-radius: 5px;
          margin-bottom: 10px;
        }
        .dashboard-item h2,
        .dashboard-item h3 {
          margin-top: 0;
        }
        .toggle-container {
          display: flex;
          justify-content: center;
          align-items: center;
          margin-bottom: 20px;
        }
        .toggle-label {
          margin-right: 10px;
          font-size: 18px;
          font-weight: 600;
        }
        .toggle-slider {
          position: relative;
          width: 50px;
          height: 24px;
          background-color: #ccc;
          border-radius: 12px;
          cursor: pointer;
        }
        .toggle-slider::before {
          content: '';
          position: absolute;
          width: 20px;
          height: 20px;
          background-color: #fff;
          border-radius: 50%;
          top: 2px;
          left: 2px;
          transition: transform 0.3s ease;
        }
        .toggle-slider.active::before {
          transform: translateX(26px);
        }
        :root {
          --background-color: #FAFAFA;
          --text-color: #212121;
          --primary-color: #2DF8C5;
          --secondary-color: #2DF8C5;
          --accent-color: #FFE500;
          --item-background-color: #FFFFFF;
          --list-item-background-color: #F2F62E;
        }
        .dark-mode {
          --background-color: #212121;
          --text-color: #E0E0E0;
          --primary-color: #BBE0FE;
          --secondary-color: #43A418;
          --accent-color: #FFEAB2;
          --item-background-color: #424242;
          --list-item-background-color: #616161;
        }
        .dashboard-item ul li {
          background: #E0E0E0; /* Soft grey background */
        }
      </style>
    </head>
    <body class="light-mode">
      <div class="container">
        <div class="dashboard-header">
          <h1>Dashboard</h1>
          <img src="https://www.crio.do/static/5ac1a39f9eebf6fe904e14069500353e/fd8b5/Crio_Dark.png" alt="Crio Logo">
        </div>
        
        <div class="toggle-container">
          <span class="toggle-label">Light/Dark Mode</span>
          <div class="toggle-slider" onclick="toggleMode()"></div>
        </div>

        <div class="dashboard-item">
          <label>No. of Queries:</label>
          <span>${responseData.status.noOfQueries}</span>
        </div>

        <div class="dashboard-item">
          <label>Overall Quality:</label>
          <span>${responseData.status.overallQuality}</span>
        </div>

        <div class="dashboard-item">
          <h2>Inline Suggestions</h2>
          <ul>
            ${responseData.inlineSuggestion
              .map(
                (suggestion: any) =>
                  `<li>Line ${suggestion.lineNumber}: ${suggestion.text}</li>`
              )
              .join("")}
          </ul>
        </div>

        <div class="dashboard-item">
          <h3>CheckStyle</h3>
          <ul>
            <li>Code Reusability (${
              responseData.checkStyle.checkboxes[0] ? "Passed" : "Violated"
            })</li>
            <li>Secrets of constant and keys (${
              responseData.checkStyle.checkboxes[1] ? "Passed" : "Violated"
            })</li>
            <li>Code Structure (pre-provided by us)</li>
            <li>Naming Conventions (${
              responseData.checkStyle.checkboxes[3] ? "Passed" : "Violated"
            })</li>
            <li>Industrial comments naming Structure (${
              responseData.checkStyle.checkboxes[4] ? "Passed" : "Violated"
            })</li>
            <li>Cohesion (well defined functionality)</li>
            <li>Loose Coupling</li>
            <li>Avoiding Over Complexity</li>
            <li>Encapsulation</li>
            <li>DeadCode (unused code/methods)</li>
          </ul>
        </div>

        <div class="dashboard-item">
          <h3>Summary</h3>
          <p><strong>Strength:</strong> ${responseData.summary.strength}</p>
          <p><strong>Scope for Improvement:</strong> ${
            responseData.summary.scopeForImprovement
          }</p>
        </div>

        <div class="dashboard-item">
          <h3>Occurrence Line Numbers</h3>
          <p>${responseData.checkStyle.occuranceLineNumbers
            .map((lines: number[]) =>
              lines.length > 0 ? lines.join(", ") : "No occurrences"
            )
            .join("<br>")}</p>
        </div>
      </div>

      <script>
        function toggleMode() {
          document.body.classList.toggle('dark-mode');
          document.body.classList.toggle('light-mode');
          const slider = document.querySelector('.toggle-slider');
          slider.classList.toggle('active');
        }
      </script>
    </body>
    </html>
  `;
  return htmlContent;
}

export function activate(context: vscode.ExtensionContext) {
  console.log(
    'Congratulations, your extension "pragna" is now active in the web extension host!'
  );

  const helloCommand = vscode.commands.registerCommand(
    "pragna.helloWorld",
    () => {
      vscode.window.showInformationMessage(
        "Hello Robert from pragna in a web extension host!"
      );
    }
  );

  const scanCommand = vscode.commands.registerCommand(
    "pragna.scan",
    async () => {
      console.log("Pragna Scan command executed");

      const project = await vscode.window.showQuickPick(projectNames, {
        placeHolder: "Select a project",
      });

      if (project) {
        const activeEditor = vscode.window.activeTextEditor;
        if (activeEditor) {
          const document = activeEditor.document;
          const textContent = document.getText();
          const fileName = document.fileName.substring(
            document.fileName.lastIndexOf("/") + 1
          );

          console.log(`Project Selected: ${project}`);
          console.log(`File Name: ${fileName}`);
          console.log(`Text Content: ${textContent}`);

          // Make HTTP GET request to fetchDashboard
          await fetchDashboard(fileName, textContent, project);
        } else {
          vscode.window.showInformationMessage("No active editor found.");
        }
      }
    }
  );

  context.subscriptions.push(helloCommand);
  context.subscriptions.push(scanCommand);
}

export function deactivate() {}
