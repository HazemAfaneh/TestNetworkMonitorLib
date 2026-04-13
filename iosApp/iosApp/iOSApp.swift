import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        AppModuleKt.doInitKoin()
        #if DEBUG
        NetworkInspectionPro.shared.enable()
        #endif
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
