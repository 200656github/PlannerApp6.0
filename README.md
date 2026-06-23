# PlannerApp 5 - 任务和支出管理应用

一款采用 **Jetpack Compose** + **Room** + **Coroutines** 构建的现代化 Android 任务和支出管理应用，专为 **荣耀 X40 GT** 及其他高端 Android 设备优化。

## ✨ 功能特性

### 📋 任务管理
- ✅ 创建、编辑、删除任务
- 📅 按日期查看和管理任务
- 🔔 支持任务描述和详细信息
- 💫 流畅的 UI 动画和交互

### 📊 日历视图
- 📆 月历显示和日期导航
- 🎯 快速查看每日任务
- 📍 当前日期高亮显示
- 🔄 实时数据同步

### 💰 支出跟踪
- 💸 记录日常开支
- 🏷️ 支持多个支出分类
- 📈 日度统计和总额计算
- 🎨 直观的分类图标

### ⚙️ 高级特性
- 🌓 Material Design 3 设计语言
- ⚡ 高性能的 Compose UI
- 💾 本地 SQLite 数据库（无需云同步）
- 🔒 隐私保护（数据仅存储在本地）
- 📱 完全响应式布局
- 🎯 为 **144Hz 屏幕** 优化

## 🚀 快速开始

### 前置要求

- **Android Studio Giraffe** 或更新版本
- **Java 17** 或更新版本
- **Gradle 8.4+**
- **Android SDK**: API 34

### 本地开发

```bash
# 克隆仓库
git clone https://github.com/你的用户名/PlannerApp.git
cd PlannerApp

# 使用 Android Studio 打开项目
# 或使用命令行构建

# 构建 Debug APK
./gradlew assembleDebug

# 运行测试
./gradlew test
```

### 生成 Release APK

```bash
# 构建 Release 版本
./gradlew assembleRelease

# APK 位置：app/build/outputs/apk/release/app-release.apk
```

## 📦 使用 GitHub Actions 自动构建

### 设置步骤

1. **推送代码到 GitHub**
   ```bash
   git push origin main
   ```

2. **查看自动构建**
   - 进入仓库 **Actions** 标签页
   - 工作流 "Build Debug & Release APK" 自动运行
   - 等待 3-6 分钟完成

3. **下载 APK**
   - 点击最新的构建运行
   - 点击 **Artifacts** 下载 APK 文件

### 手动触发构建

```bash
# 通过 GitHub CLI
gh workflow run build-apk.yml -f build_type=release

# 或直接在网页 Actions 页面点击 "Run workflow"
```

## 📱 设备适配

### 推荐设备
- ✅ **荣耀 X40 GT** (主要测试设备)
- ✅ 高通骁龙 888 及更新处理器
- ✅ 6GB+ RAM
- ✅ 144Hz+ 高刷屏幕

### 兼容性
- ✅ Android 8.0+ (API 26+)
- ✅ 所有主流品牌手机
- ✅ 平板设备

### 荣耀 X40 GT 专项优化

详见 [HONOR_X40_GT_ADAPTATION.md](HONOR_X40_GT_ADAPTATION.md)

关键优化：
- 🎯 144Hz 屏幕流畅度优化
- 🔋 电池续航优化
- 📱 MagicOS 权限兼容性
- 🌡️ 散热友好的 UI 更新

## 📋 项目结构

```
PlannerApp/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/planner/
│   │       │   ├── MainActivity.kt          # 主界面
│   │       │   ├── PlannerApplication.kt    # 应用入口
│   │       │   ├── ui/                      # UI 层
│   │       │   │   ├── calendar/           # 日历模块
│   │       │   │   ├── tasklist/           # 任务列表模块
│   │       │   │   ├── expense/            # 支出模块
│   │       │   │   ├── edit/               # 编辑屏幕
│   │       │   │   ├── theme/              # 主题配置
│   │       │   │   └── components/         # 通用组件
│   │       │   ├── data/                   # 数据层
│   │       │   │   ├── local/              # Room 数据库
│   │       │   │   └── repository/         # 数据仓库
│   │       │   └── domain/                 # 业务逻辑
│   │       │       └── model/              # 数据模型
│   │       └── res/                        # 资源文件
│   └── build.gradle.kts                   # 模块构建配置
├── .github/workflows/
│   └── build-apk.yml                      # GitHub Actions 工作流
├── build.gradle.kts                       # 项目构建配置
├── settings.gradle.kts                    # 模块配置
├── gradle.properties                      # Gradle 属性
└── README.md                              # 本文件
```

## 🏗️ 技术栈

### UI 框架
- **Jetpack Compose** 1.5.5+
- **Material Design 3**
- **Material Icons Extended**

### 数据存储
- **Room** 2.6.1 (SQLite ORM)
- **KSP** (Kotlin Symbol Processing)

### 异步和状态管理
- **Coroutines** 1.7.3+
- **Flow** (响应式编程)
- **ViewModel** & **StateFlow**

### 导航
- **Navigation Compose** 2.7.5+

### 构建
- **Kotlin** 1.9.21+
- **Gradle** 8.4+
- **JDK** 17

## ✅ 代码质量

### 已修复的问题

1. ✅ **ExperimentalCoroutinesApi 警告**
   - 为使用 `flatMapLatest` 的 ViewModel 添加 `@OptIn` 注解
   - 确保清晰的 API 契约

2. ✅ **缺失的 Coroutines 导入**
   - 显式声明 `kotlinx-coroutines-core` 依赖
   - 避免间接依赖脆弱性

3. ✅ **GitHub Actions 工作流优化**
   - 使用 `gradle/actions/setup-gradle@v3`
   - 支持 Debug 和 Release 构建
   - 自动产物管理和保留

### 代码规范
- 遵循 Kotlin 官方风格指南
- 函数式编程风格
- 明确的错误处理
- 完整的 null 安全

## 📖 开发文档

### 添加新功能

#### 1. 创建新的 ViewModel
```kotlin
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.StateFlow

class MyViewModel(application: Application) : AndroidViewModel(application) {
    // 实现逻辑
}
```

#### 2. 定义 Room 实体
```kotlin
@Entity(tableName = "my_table")
data class MyEntity(
    @PrimaryKey val id: Long = 0,
    val name: String = ""
)
```

#### 3. 创建 Composable 屏幕
```kotlin
@Composable
fun MyScreen(
    viewModel: MyViewModel = hiltViewModel()
) {
    // UI 逻辑
}
```

### 常见任务

- 💾 添加新数据模型：见 `domain/model/`
- 📊 创建新的数据仓库：见 `data/repository/`
- 🎨 自定义主题：见 `ui/theme/Color.kt`
- 📱 添加新屏幕：见 `ui/` 下的各模块

## 🐛 故障排除

### 构建失败

**问题**: `Gradle sync failed`
```
解决方案：
1. File → Invalidate Caches → Invalidate and Restart
2. 删除 .gradle 目录
3. ./gradlew clean build
```

**问题**: `Kotlin compiler error`
```
解决方案：
1. 确保 JDK 17+: File → Project Structure
2. 更新 Kotlin 插件：Tools → Kotlin → Configure
3. 清理并重新构建
```

### 运行时问题

- **App 闪退**：检查 logcat 日志 (`adb logcat | grep PlannerApp`)
- **权限被拒**：在手机设置中手动授予权限
- **数据丢失**：Clear App Data 会删除所有本地数据

详见 [HONOR_X40_GT_ADAPTATION.md](HONOR_X40_GT_ADAPTATION.md) 的故障排除部分。

## 🤝 贡献

欢迎提交 Pull Request 或 Issue！

### 提交 PR 的步骤

1. Fork 此仓库
2. 创建特性分支 (`git checkout -b feature/amazing-feature`)
3. Commit 更改 (`git commit -m 'Add amazing feature'`)
4. Push 到分支 (`git push origin feature/amazing-feature`)
5. 开启 Pull Request

## 📄 许可证

此项目采用 **MIT 许可证**。详见 [LICENSE](LICENSE) 文件。

## 📞 联系方式

- **GitHub Issues**: 报告 bug 和功能请求
- **Email**: 支持邮箱 (如有)

## 🎯 未来计划

- [ ] 支出分类图表分析
- [ ] 任务提醒通知
- [ ] 数据导出 (CSV/PDF)
- [ ] 深色主题完全支持
- [ ] 多语言国际化
- [ ] 云同步备份（可选）

## 📊 项目统计

| 指标 | 数值 |
|------|------|
| Kotlin 代码行数 | ~2000 |
| Compose 组件数 | 15+ |
| 数据库表数 | 3 |
| GitHub Stars | ⭐ 社区的支持 |
| 构建时间 | ~3-6 分钟 (CI/CD) |

---

**开发者**: [您的名字]  
**最后更新**: 2026年6月  
**项目版本**: 1.0.0  
**针对设备**: 荣耀 X40 GT (8GB+, MagicOS 6.1/7.0)

Made with ❤️ using Jetpack Compose
