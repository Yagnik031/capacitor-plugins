import Foundation

@objc public class Flash: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
