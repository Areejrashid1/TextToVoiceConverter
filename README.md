# Text-to-Speech Converter

A simple, interactive web-based application that converts user-inputted text into synthesized speech. The application leverages the native browser **Web Speech API**, allowing users to write custom text, choose from a list of available system voices, and hear the text read aloud.

---

## 🚀 Features

* **Native Speech Synthesis:** Utilizes the built-in browser engine without needing any external audio APIs or cloud services.
* **Dynamic Voice Selection:** Automatically detects and loads all supported synthesized voices available on the user's operating system/browser.
* **Real-time Voice Swapping:** Allows the user to toggle between different voice profiles (e.g., accents, languages, genders) before playing the audio.

---

## 🧠 Deep-Dive JavaScript Logic (Study Notes)

This code is an excellent study guide for understanding asynchronous browser APIs, system hardware integration, and standard DOM manipulation.

### 1. Initializing Core Elements

```javascript
let speech = new SpeechSynthesisUtterance();
let voices = [];
let voiceSelect = document.querySelector('select');

```

* **`SpeechSynthesisUtterance()`**: This is a native constructor provided by the Web Speech API. Think of it as creating a "Speech Request Package". It holds vital information about *what* to say (text) and *how* to say it (voice type, pitch, speed).
* **`voices` Array**: A blank array used to store the hardware/browser voice profiles once they load.

### 2. The Asynchronous Voice Loading Hook

```javascript
window.speechSynthesis.onvoiceschanged = () => {
    voices = window.speechSynthesis.getVoices();
    speech.voice = voices[0];
    voiceSelect.innerHTML = ''; 

    voices.forEach((voice, i) => {
        voiceSelect.options[i] = new Option(voice.name, i);
    });
}

```

* **`onvoiceschanged` Event**: Browsers load text-to-speech engine profiles asynchronously. This event fires automatically the exact moment the browser finishes fetching the available system voices.
* **`getVoices()`**: Retrieves an array of all available speech engines (e.g., "Google US English", "Microsoft David", "Apple Samantha").
* **`new Option(text, value)`**: A clean, native JavaScript way to generate an HTML `<option>` element.
* `text`: What the user sees in the dropdown (`voice.name`).
* `value`: The underlying data value, mapped here directly to its index `i` in the array.



> 📝 **Note:** Clearing the dropdown via `voiceSelect.innerHTML = '';` ensures that if `onvoiceschanged` triggers multiple times (common in Chromium-based browsers), voices aren't duplicated in the UI.

### 3. Activating the Synthesis Engine

```javascript
document.querySelector("button").addEventListener("click", () => {
    speech.text = document.querySelector("textarea").value;
    
    const selectedVoiceIndex = voiceSelect.value;
    speech.voice = voices[selectedVoiceIndex];

    window.speechSynthesis.speak(speech);
});

```

* **`speech.text = ...`**: Grabs the string inside the `<textarea>` box and feeds it into our speech request package.
* **Binding the Selection**: `voiceSelect.value` returns the numerical index (`i`) assigned in the previous step. We pass that index back into our master `voices` array to extract the exact voice object selected by the user, assigning it to `speech.voice`.
* **`window.speechSynthesis.speak(...)`**: The ultimate trigger command. It adds our configured `speech` instance into the browser's audio queue and begins playout immediately.

---

## 🎨 Layout Contract (HTML Structuring)

For the script to resolve selectors safely without crashing, the DOM must contain these specific core structural tags:

| Target Selector | Element | Purpose |
| --- | --- | --- |
| `textarea` | Text Entry | Captures the raw text input from the user. |
| `select` | Dropdown | Form element populated by JavaScript to present voice choices. |
| `button` | Trigger | Event listener target that fires the execution mechanism. |

---

## 📝 Key Takeaways for Interviews/Review

* **Hardware Dependability:** The voice options change radically depending on the host machine. A user running Chrome on an Android phone will see different voices than a user running Safari on an iPhone.
* **`SpeechSynthesis` vs `SpeechSynthesisUtterance`:** * `SpeechSynthesis` is the actual master controller (the hardware engine).
* `SpeechSynthesisUtterance` is an individual data packet (the sentence data and style configs).


* **The `value` Mapping Strategy:** Using array indices (`i`) as the value attribute for `<option>` tags is a highly effective design pattern for mapping UI configurations directly back to internal state arrays.
