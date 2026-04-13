import SwiftUI
import shared

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
